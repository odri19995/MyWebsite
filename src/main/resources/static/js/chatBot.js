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
            
            $(".wrap").append(appendHtml);

            GetOpenAIResponse();
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
        