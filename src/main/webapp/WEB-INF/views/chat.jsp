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

<style type="text/css">
.ChatWrapper {
   width: 100%;
   height: 100%;
}

#Chatting {
   width: 60%;
   height: 400px;
   border : 1px solid gray;
}

#Chatting-send{
   width: 60%;
}
.content {
   width: 100%;
   height: 5%;
}
.my{
   text-align : right;
}
.others{
   text-align : left;
}
</style>

</head>
<body>

   <div class="container">
      <h2>WebSocket Chatting</h2>
      <div class="panel panel-default">
         <div class="panel-heading">채팅구현하기</div>
         <div class="panel-body" align="center">
         
            <div class="ChatWrapper">
               <div id="Chatting">
               </div>
               
               <br>
               
               <div id = "Chatting-send">
                  <div class="form-group">
                     <label class="control-label col-sm-2" for="nickname">닉네임:</label>
                     <div class="col-sm-8">
                        <input type="email" class="form-control" id="nickname"
                           placeholder="닉네임입력" name="nickname">
                     </div>
                     <button id="eCheck" class="btn btn-success col-sm-2">닉네임확정</button>
                  </div>
                  <br>
                  <div class="form-group">
                     <label class="control-label col-sm-2" for="content">메시지:</label>
                     <div class="col-sm-8">
                        <textarea placeholder="메시지입력" rows="5" class="form-control" id="content" name="content"></textarea>
                     </div>
                  </div>
                  <div class="form-group">
                     <button class="btn btn-info col-sm-2" id="sendMsg" disabled>전송</button>                  
                  </div>
               </div>

            </div>

         </div>
         <div class="panel-footer">클라우드 분석서비스 개발자과정 - 전대원</div>
      </div>
   </div>
   <script>
   // 닉네임을 입력하고 닉네임 확정 버튼을 클릭하면
   // 순수 js코드로 input태그를 통해 입력 받은 닉네임을 가져오기
   		let nickbtn = document.getElementById("eCheck");
   		let nickname = document.getElementById("nickname");
   		let sendMSG = document.getElementById("sendMsg");
   		let content = document.getElementById("content");
   		nickbtn.addEventListener("click",() => {
   			console.log(nickname.value);
   			// 닉네임 확정되면 input 태그 읽기전용으로 바꾸기
   			if(nickname.value != ''){
   				nickname.readOnly = true;
   			// 전송버튼 활성화
   				sendMSG.removeAttribute("disabled")
   			}
   		})
   </script>
</body>
</html>