<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<!-- 테일윈드 불러오기 -->
<!-- 노말라이즈, 라이브러리 -->
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.7/tailwind.min.css" /> -->
<!-- 데이지 UI -->
<!-- <link href="https://cdn.jsdelivr.net/npm/daisyui@2.31.0/dist/full.css" rel="stylesheet" type="text/css" /> -->
<!-- 제이쿼리 불러오기 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<!-- 폰트어썸 불러오기 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />
<link rel="stylesheet" href="/css/chatBot.css">
<link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
<head>
    <title>Chatbot</title>
    <script>
        // 챗봇 응답을 생성하는 함수
        function GetBotResponse() {
            var userText = document.getElementById("userInput").value;
            var botResponse = "";
            
            // 챗봇 응답 로직
            if (userText.toLowerCase() === "안녕") {
                botResponse = "안녕하세요!";
            }
            // 챗봇 응답을 화면에 표시
            document.getElementById("chatLog").innerHTML += "<br>사용자: " + userText;
            GetOpenAIResponse();
        }
        
        function GetOpenAIResponse() {
        	$.get('getchatbot', {
        		userInput : $("#userInput").val()
			}, function(data){
	 			console.log(data);
	            document.getElementById("chatLog").innerHTML += "<br>챗봇: " + data;
			}, 'text')
		}
        
    </script>
</head>
<body>
	<div id="menu">
		<ul>
		    <li id="logo">ChatBot</li>
		    <li><a href="<c:url value='/'/>">Home</a></li>
		    <li><a href="<c:url value='/board/list'/>">Board</a></li>
			<c:if test="${sessionScope.id == null }">
				<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/login"><span>login</span></a></li>
			</c:if>
			<c:if test="${sessionScope.id != null  }">
				<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/logout"><span>logout</span></a></li>
			</c:if>
		    <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
		    <li><a href="<c:url value='/usr/openai/chatbot'/>">chatbot</a></li>
		    
		    <li><a href=""><i class="fas fa-search small"></i></a></li>
		</ul> 
	</div>
	
	
	 <h1>Make your chatBot</h1>
	<div id="chatLog">	</div>
	 <div class="wrap">
        <div class="chat ch1">
            <div class="icon"><i class="fa-solid fa-user"></i></div>
            <div class="textbox">안녕하세요. 반갑습니다.</div>
        </div>
        <div class="chat ch2">
            <div class="icon"><i class="fa-solid fa-user"></i></div>
            <div class="textbox">안녕하세요. 친절한효자손입니다. 그동안 잘 지내셨어요?</div>
        </div>
        <div class="chat ch1">
            <div class="icon"><i class="fa-solid fa-user"></i></div>
            <div class="textbox">아유~ 너무요너무요! 요즘 어떻게 지내세요?</div>
        </div>
        <div class="chat ch2">
            <div class="icon"><i class="fa-solid fa-user"></i></div>
            <div class="textbox">뭐~ 늘 똑같은 하루 하루를 보내는 중이에요. 코로나가 다시 극성이어서 모이지도 못하구 있군요 ㅠㅠ 얼른 좀 잠잠해졌으면 좋겠습니다요!</div>
        </div>
    </div>
    <span>
	   	<input type="text" id="userInput" name="userInput" placeholder="메시지 입력" />
	    <button type="button" onclick="GetBotResponse()">전송</button>
	</span>    
</body>
</html>