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
		<!-- 검색할 공간 생성  -->
            <form action="${cpath}/" method ="post" class = "form-inline">
	            <div class="form-group">
				  <label for="sel1">검색조건:</label>
				  <select class="form-control" name = "filter" id="sel1">
				    <option>작성자</option>
				    <option>제목</option>
				    <option>내용</option>
				  </select>
				</div>
				<div class = "form-group">
					<input type = "text" class = "form-control" name = "searchContent">
					<button type = "submit" class = "btn btn-success">검색</button>
				</div>
            </form>
            <div class="panel-body">
               <table class="table table-bordered table-hover table-striped">
                  <tr>
                     <th>번호</th>
                     <th>제목</th>
                     <th>작성자</th>
                     <th>작성일</th>
                     <th>조회수</th>
                  </tr>
                  <c:forEach items="${boardList}" var="b">
                     <tr>
                        <td>${b.idx}</td>
                        <td><a href = "goBoardContent/${b.idx}">${b.title}</a></td>
                        <td>${b.writer}</td>
                        <td>${b.indate}</td>
                        <td>${b.count}</td>
                     </tr>
                  </c:forEach>
               </table>
               <button onclick = "location.href='${cpath}/goInsert'" class = "btn btn-success">글 작성하기</button>
            </div>
         </div>
      </div>
   </div>

</body>
</html>