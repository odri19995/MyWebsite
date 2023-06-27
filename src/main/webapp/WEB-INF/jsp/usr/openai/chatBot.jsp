<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<!-- 제이쿼리 불러오기 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<!-- 테일윈드 치트 시트 불러오기 -->
<!-- 노말라이즈, 라이브러리 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.7/tailwind.min.css" />
<!-- 데이지 UI -->
<link href="https://cdn.jsdelivr.net/npm/daisyui@2.31.0/dist/full.css" rel="stylesheet" type="text/css" />
<!-- 폰트어썸 불러오기 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />
<link rel="stylesheet" href="/css/chatBot.css">
<link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <title>Chatbot</title>
    <script>
        // 챗봇 응답을 생성하는 함수
        function GetBotResponse() {
            var userText = document.getElementById("userInput").value;
            var htmlUser = '<div class="textbox">'+ userText + '</div>';
 			// \$ 스크립트내의 변수를 el 처럼 사용하는 방법 아닐경우 +로 이어줘야한다. 
            // 챗봇 응답을 화면에 표시
            let appendHtml = `<div class="chat ch2">
            					<div class="icon"><i class="fa-solid fa-user"></i></div>
            					\${htmlUser}
            				</div>`;
            
            let userMsg = document.getElementById("userInput").value.trim()
            
            if(userMsg.length>0){
            	$(".wrap").append(appendHtml);
            	GetOpenAIResponse();
            }else{
            	alert("내용을 입력해주세요");
            }
        }
        
        function GetOpenAIResponse() {
        	$.get('getchatbot', {
        		userInput : $("#userInput").val()
			}, function(data){
				var htmlBot = '<div class="textbox">'+ data + '</div>';
	 			console.log(data);
	 			// \$ 스크립트내의 변수를 el 처럼 사용하는 방법 아닐경우 +로 이어줘야한다. 
	 			let appendHtml = `<div class="chat ch1">
									<div class="icon"><i class="fa-solid fa-user"></i></div>
									\${htmlBot}
								</div>`;
	 			
	 			$(".wrap").append(appendHtml);
			}, 'text')
		}
        
//      Enter click event
        $(document).ready(function() {
            $("#userInput").keyup(function(event) {
                if (event.which === 13) {
                    $("#myBtn").click();
                }
            });
         
            $("#myBtn").click(function() {
                console.log('clicked!');
            })
        });
        
    </script>
</head>
<body>
	<section>
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
		 
		 <div class="form-control">
 			 <label class="label cursor-pointer">
    			<input type="checkbox" class= "checkbox-instruction"/>
   				 <span class="label-text"> 지시문 추가하기 </span>
  			</label>
		</div>
		
		<script>
			let chk = $("input[type=checkbox]");			
			chk.on("click", function() {
				if (chk.is(":checked")==true){
					console.log("체크된 상태");
					addAttributeBlock();
				}
				if (chk.is(":checked")==false){
					console.log("체크 안된 상태");
					addAttributeNone();
				}
			});
			function addAttributeNone() {
// 			    document.getElementsByClassName("form-control").style.display = 'none';
				$('.instructbox').css('display', 'none');
			}
			function addAttributeBlock() {
				$('.instructbox').css('display', 'block');
			}
			
		</script>
		 
		 <div class="instructbox">
		 	<div class="instruction">
		 		<input  class = rounded type="text" id="userInstruct" name="userInstruct" placeholder="&nbsp;&nbsp;지시문을 입력해주세요" />	
		 	</div>
		 </div>
		 
		 <div class="wrap">
	        <div class="chat ch1" >
	            <div class="icon"><i class="fa-solid fa-user"></i></div>
	            <div class="textbox">안녕하세요. 반갑습니다.</div>
	        </div>
	    </div>
	    <span class ="flex justify-end mt-1 mr-14" >
		   	<input  class = rounded type="text" id="userInput" name="userInput" placeholder="&nbsp;메시지 입력" />
		    <button id="myBtn" class="ml-2 btn-text-link btn btn-active" onclick="GetBotResponse()">전송</button>
		</span>
	</section>    
</body>
</html>