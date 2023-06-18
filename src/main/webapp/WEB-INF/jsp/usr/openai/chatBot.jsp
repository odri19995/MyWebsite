<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	    <h1>Chatbot Template</h1>
	    <div id="chatLog"></div>
	    <input type="text" id="userInput" name="userInput" placeholder="메시지 입력" />
	    <button type="submit" onclick="GetBotResponse()">전송</button>
</body>
</html>