<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Chatbot</title>
    <script>
        // 챗봇 응답을 생성하는 함수
        function getBotResponse() {
            var userText = document.getElementById("userInput").value;
            var botResponse = "";
            
            // 챗봇 응답 로직
            if (userText.toLowerCase() === "안녕") {
                botResponse = "안녕하세요!";
            } else if (userText.toLowerCase() === "날씨") {
                botResponse = "오늘 날씨는 맑아요.";
            } else {
                botResponse = "죄송해요, 잘 이해하지 못했어요.";
            }
            
            // 챗봇 응답을 화면에 표시
            document.getElementById("chatLog").innerHTML += "<br>사용자: " + userText;
            document.getElementById("chatLog").innerHTML += "<br>챗봇: " + botResponse;
        }
    </script>
</head>
<body>
	<form action="/usr/openai/getchatbot" method="POST">	
	    <h1>Chatbot Template</h1>
	    <div id="chatLog"></div>
	    <input type="text" id="userInput" name="userInput" placeholder="메시지 입력" />
	    <button type="submit" onclick="getBotResponse()">전송</button>
	</form> 
</body>
</html>