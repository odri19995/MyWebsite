<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html lang="ko">
<!-- 테일윈드 불러오기 -->
<!-- 노말라이즈, 라이브러리 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.7/tailwind.min.css" />
<!-- 데이지 UI -->
<link href="https://cdn.jsdelivr.net/npm/daisyui@2.31.0/dist/full.css" rel="stylesheet" type="text/css" />
<!-- 제이쿼리 불러오기 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<!-- 폰트어썸 불러오기 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />
<link rel="stylesheet" href="/css/loginForm.css" />
<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.1.0/kakao.min.js"
  integrity="sha384-dpu02ieKC6NUeKFoGMOKz6102CLEWi9+5RQjWSV0ikYSFFd8M3Wp2reIcquJOemx" crossorigin="anonymous"></script>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
 
</head>
<body>
    <form action="<c:url value="/usr/login"/>" method="post" onsubmit="return formCheck(this);">
        <h3 id="title">Login</h3>
        <div id="msg">
	    <c:if test="${not empty param.msg}">
		<i class="fa fa-exclamation-circle"> ${URLDecoder.decode(param.msg)}</i>            
	    </c:if>        
		</div>
        <input type="text" name="id" value ="${cookie.id.value}" placeholder="이메일 입력" autofocus value = "">
        <input type="password" name="pwd" placeholder="비밀번호">
        <button>로그인</button>
        <div>
            <label><input type="checkbox" name="rememberId" ${empty cookie.id.value ? "" : "checked"}> 아이디 기억</label> |
            <a href="">비밀번호 찾기</a> |
            <a href="">회원가입</a>
        </div>

		<script>
			function GetkakaoKey(){
				$.get('../usr/kakao/kakaokey', {
					
				}, function(data){
		 			Kakao.init(data);
		 			console.log(Kakao.isInitialized());
				}, 'text')
			}
			
			GetkakaoKey();
			
			function loginWithKakao() {
				    Kakao.Auth.authorize({
				      redirectUri: 'http://localhost:8081/usr/kakao/kakao',
				    });
				  }
		</script> 
		<div class="mt-4">      
	        <a id="kakao-login-btn" href="javascript:loginWithKakao()">
	  			<img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="222"
	    	alt="카카오 로그인 버튼" />
			</a>	
    	</div> 
        <script>
            function formCheck(frm) {
                 let msg ='';
     
                 if(frm.id.value.length==0) {
                     setMessage('id를 입력해주세요.', frm.id);
                     return false;
                 }
     
                 if(frm.pwd.value.length==0) {
                     setMessage('password를 입력해주세요.', frm.pwd);
                     return false;
                 }

                 return true;
            }
     
            function setMessage(msg, element){
                 document.getElementById("msg").innerHTML = ` ${'${msg}'}`;
     
                 if(element) {
                     element.select();
                 }
            }
        </script>
    </form>
</body>
</html>