<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var = "cpath" value = "${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!-- jQuery library -->
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<meta charset="UTF-8">

</head>
<body>
   <div class="jumbotron">
      <h1>클라우드 과정 게시판</h1>
      <p>Spring을 활용해서 간단한 게시판을 만들어보자!</p>
   </div>
   <div class="container">
      <div class="panel panel-default">
         <div class="col">
            <div class="panel-heading">SpringMVC 게시판</div>
            <div class="panel-body">
               <table class="table table-bordered table-hover">
                  <tr>
                     <th>번호</th>
                     <td>${result.idx}</td>
                  </tr>
                  <tr>
                     <th>제목</th>
                     <td>${result.title}</td>
                  </tr>
                  <tr>
                     <th>내용</th>
                     <td>${result.content}</td>
                  </tr>
                  <tr>
                     <th>파일</th>
                     <td><img src="${cpath}/upload/${result.file}"></td>
                  </tr>
                  <tr>
                     <th>작성자</th>
                     <td>${result.writer}</td>
                  </tr>
                  <tr>
                     <th>작성일</th>
                     <td>${result.indate.getYear()+1900}년 ${result.indate.getMonth() + 1}월 ${result.indate.getDate()}일 ${result.indate.getHours()}시 ${result.indate.getMinutes()}분 ${result.indate.getSeconds()}초</td>
                  </tr>
                  <tr>
                     <th>조회수</th>
                     <td>${result.count}</td>
                  </tr>
               </table>
            </div>
         </div>
      </div>
   </div>
  
  <!-- 조회수 기능을 만들기 위해 axios CDN 다운로드 코드 추가 -->
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
	// 1. axios 통신을 사용해서 조회수를 1 증가시키자!
	// 	--> fetch함수랑 비슷한데 더 간단하게 사용가능하다!
	//	요청 URL : updateBoard
	//	보내줘야할 데이터 : 게시글 번호
	//	요청 방식 : get
	//	  성공 : console에 성공! 출력
	axios.get("${cpath}/updateBoard",{
		params:{
			// 객체 형태로 묶기
			idx : ${result.idx}
		}
	}).then(res => {
		console.log(res)
	}).catch(err =>{
		console.log(err);
	})
	
	
	
	
	
	
</script>
</body>
</html>