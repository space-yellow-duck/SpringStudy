package kr.cloud.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.cloud.web.entity.Board;
import kr.cloud.web.entity.SearchCriteria;
import kr.cloud.web.mapper.BoardMapper;

@Controller
public class MyController {
	@Autowired
	BoardMapper mapper;
	
	// log찍는 도구 가져오기
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	// 기능 단위로 메서드 생성
	
	@GetMapping("/goChat")
	public String goChat() {
		return "chat";
	}
	
	
	
	// 글 작성하기
	// step1: board.jsp 에서 버튼을 클릭하면 goInsert로 url요청한다.
	//			-> goInsert요청을 받았을 때, boardInsert.jsp로 이동!
	//			--> getMapping(단순 이동) goInsert.jsp로 이동!
	// step2 : boardInsert.jsp에서 form태그를 통해 데이터를 입력 받아
	//			post방식으로 goInsert로 DB에 데이터 저장 요청한다.
	//			--> postMapping goInsert요청 받아 데이터를 DB에 저장하는 메서드
	//			
	@GetMapping("/goInsert")
	public String goInsert() {
		return "boardInsert";
	}
	
	@PostMapping("/goInsert")
	public String boardInsert(Board board,MultipartFile upload) throws IllegalStateException, IOException {
		// 매개변수에 MultipartFile(내가 전송한 파일 데이터 수집)
		// --> MultipartFile객체를 받아오겠다 라고 매개변수에 작성!
		//	.jsp에서 데이터를 받아올때, input태그의 name값을 기준으로 해서
		//		매개변수에 선언(명시)해야한다!
		// 중복된 사진(파일)을 저장할 때, 고유 식별자를 생성해서 파일명에 추가
		UUID uuid = UUID.randomUUID();
		
		// 0. 파일이 잘 넘어오는지 파일의 이름 꺼내보기!
		String fileName = uuid.toString()+"_"+upload.getOriginalFilename();
		logger.info("파일네임 확인하기>>"+fileName);
		// 1) 파일 업로드 경로 관련 직접 코드 작성하기 \\ upload \\ 
		String path = "C:\\Users\\smhrd\\Desktop\\Spring\\SpringBoot2\\src\\main\\resources\\static\\upload\\";
		// 2) 파일을 upload폴더에 저장(업로드)
		upload.transferTo(new File(path+fileName));
		// 3) board자료형으로 DB에 저장! -> file필드에도 새롭게 데이터 추가
		logger.info("goInsert를 통해 수집한 데이터 >>" + board);
		// 4) goInsert메서드 실행 전에 board자료형의 file필드에 데이터 추가
		board.setFile(fileName);
		
		
		mapper.boardInsert(board);
		
		return "redirect:/";
	}
	
	@GetMapping("/") // SpringBoot에서는 RequestMapping 사용 X
	public String home(Model model) {
		// DB에 접속해서 전체 게시글 조회하는 메서드
		List<Board> boardList = mapper.selectAll();
		model.addAttribute("boardList",boardList);
		return "Board";
	}
	
	@GetMapping("/goBoardContent/{idx}")
	public String goBoardContent(@PathVariable int idx,Model model) {
		// @PathVariable("data") : Query String 방식이 아닌
		//						경로 자체에 데이터를 포함시켰을때, 해당하는 데이터를
		//						수집하는 방법
		// *매핑{변수명} -> 매개변수의 변수명과 일치 필요!
		logger.info("pathVariable을 통해 수집한 데이터 >>" + idx);
		// 1. mapper의 메서드 goBoardContent를 이용해서 idx가 일치하는 데이터 가져오기
		Board result = mapper.goBoardContent(idx);

		// 2. model에 데이터를 담아서 boardContent.jsp로 보내기
		model.addAttribute("result",result);
		return "boardContent"; // boardContent.jsp랑 일치
	}
	
	@ResponseBody // viewname이 아닌 값을 리턴 받기 위해 명시해야함
	@GetMapping("/updateBoard")
	public boolean updateBoard(Board b) {
		logger.info("updateBoard에서 수집된 데이터 확인 >> "+b);
		// spring에서 비동기 통신을 통해 컨트롤러의 리턴값이
		// viewName이 아닌 데이터를 반환할때
		// 1) responseBody어노테이션
		// 2) pom.xml에 jackson-databind 의존성 추가
		//   --> data를 java에서 js를 전달할 때, 변환하는 라이브러리
		//			: jackson-databind
		// 1. mapper를 사용해서 조회수 1 증가시키는 DB쿼리문 작성
		//	+ mapper의 updateBoard 메서드 생성
		//	+ 메서드를 통해서 실행할 쿼리문
		//	--> idx번호가 일치하는 데이터를 찾아서 조회수 컬럼 1증가
		int row = mapper.updateBoard(b.getIdx());
		// 2. 성공했다면 true반환 (리턴값), 실패했다면 false반환
//		boolean tf = true;
//		if(row > 0)
//			
//			tf = true;
//		else
//			tf = false;
//		logger.info(""+tf);
		return row>0?true:false;
	}
	
	@PostMapping("/")
	public String home(SearchCriteria criteria,Model model) {
		logger.info("SearchCriteria를 통해 수집한 자료>>"+criteria.getFilter()+criteria.getSearchContent());
		// 1. mapper를 이용해서 criteria로 수집해온 데이터를 DB에서 조회하기
		// 	criteria에는 filter(검색조건- 작성자,제목,내용)
		//			searchContent(입력받은 검색할 데이터) 2개가 담겨있다.
		
		List<Board> boardList = mapper.search(criteria);
		// 2. model에 담아서 board.jsp로 이동
		model.addAttribute("boardList",boardList);
		return "Board";
				
	}
	
	@GetMapping("/boardDelete")
	public String boardDelete(@RequestParam int idx) {
		int row = mapper.boardDelete(idx);
		if(row>0) {
			return "redirect:/";			
		}else {
			
			return "errorPage";
		}
	}
	
}
