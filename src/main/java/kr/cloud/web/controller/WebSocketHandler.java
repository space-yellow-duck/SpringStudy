package kr.cloud.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import jakarta.websocket.server.ServerEndpoint;



@Controller
@ServerEndpoint("/websocket") // 웹 소켓 사용 시, 요청하면 소캣으로 들어올 url매핑값
public class WebSocketHandler extends TextWebSocketHandler{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	// override : alt shift S
	// 상속 받은 기능들을 원하는 형식으로 변경(오버라이드)
	// session : 기존에 사용하던 세션(HttpSession)이랑 다른 세션이다.
	//	--> client의 고유 세션 키값을 받아온다.
	// *1. 웹 소캣이 열렸을 때, 실행되는 메서드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("connection 로그 >>"+ session);
	}
	// *2. 웹 소켓이 텍스트 데이터를 전달 받았을 때, 실행되는 메서드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.info("handle 로그 >>"+session);
	}

	// *3. 웹 소켓이 닫혔을때, 실행되는 메서드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		logger.info("close 로그 >>" + session);
	}
	
	
	
	
}
