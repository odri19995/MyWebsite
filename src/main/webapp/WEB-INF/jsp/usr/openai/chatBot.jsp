<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="chatBot" />
<%@ include file="../common/head.jsp" %>
<link rel="stylesheet" href="/css/chatBot.css">
<link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <title>Chatbot</title>
<style>
#menu{
	position:fixed;
}
</style>    
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
        	
        	//변수 검증
        	let instruction = "";
        	
        	if( $("#userInstruct").val()== null){
        		instruction = "";
        	}else{
        		instruction = $("#userInstruct").val();
        	}
        	console.log(instruction);
        	
        	$.get('getchatbot', {
        		userInput : $("#userInput").val(),
        		userInstruct : instruction
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
<%@ include file="../common/menu.jsp" %>		
		 <h1 class = "ml-8">GPT와 대화하기</h1>
		<form action="doWrite" method="POST">
			<section>
				<div class = "flex justify-end mr-7">
					<div class="toast toast-top toast-end mt-20">
					  <div class="alert alert-info">
					    <span>대화를 저장할 수 있어요!</span>
					  </div>
					</div>
					<input type="text" placeholder="제목을 입력해 주세요" class="input input-bordered input-primary w-full max-w-xs " />
				</div>
				<div class = "flex justify-end mr-7 mt-4">
					<button class="btn btn-neutral">작성</button>
				</div>
			</section>
		</form>	
		 
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