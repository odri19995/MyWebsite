<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<!-- 제이쿼리 불러오기 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.1.0/kakao.min.js"
  integrity="sha384-dpu02ieKC6NUeKFoGMOKz6102CLEWi9+5RQjWSV0ikYSFFd8M3Wp2reIcquJOemx" crossorigin="anonymous"></script>
<script> 
	function GetkakaoKey(){
		$.get('../kakao/kakaokey', {
		}, function(data){
 			Kakao.init(data);
 			console.log(Kakao.isInitialized());
		}, 'text')
	}
	
	GetkakaoKey();
	
	 function requestUserInfo() {
		    Kakao.API.request({
		      url: '/v2/user/me',
		    })
		      .then(function(res) {
		        alert(JSON.stringify(res));
		      })
		      .catch(function(err) {
		        alert(
		          'failed to request user information: ' + JSON.stringify(err)
		        );
		      });
		  }

		  // 아래는 데모를 위한 UI 코드입니다.
		  displayToken()
		  function displayToken() {
		    var token = getCookie('authorize-access-token');

		    if(token) {
		      Kakao.Auth.setAccessToken(token);
		      document.querySelector('#token-result').innerText = 'login success, ready to request API';
		      document.querySelector('button.api-btn').style.visibility = 'visible';
		    }
		  }

		  function getCookie(name) {
		    var parts = document.cookie.split(name + '=');
		    if (parts.length === 2) { return parts[1].split(';')[0]; }
		  }
		</script>
	

</script>
<head>
	<meta charset="UTF-8">
    <title>mainPage</title>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>    
</head>
<body>
<div id="menu">
	<ul>
	    <li id="logo">mainpage</li>
	    <li><a href="<c:url value='/'/>">Home</a></li>
	    <li><a href="<c:url value='/board/list'/>">Board</a></li>
	    <li><a href="<c:url value='/usr/login'/>">login</a></li>    
	    <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
	    <li><a href=""><i class="fas fa-search small"></i></a></li>
	</ul> 
</div>
<div style="text-align:center">
	<h1>This is HOME</h1>
	<h1>This is HOME</h1>
	<h1>This is HOME</h1>
	<p id="token-result">ㄴㄴ</p>
	<button class="api-btn" onclick="requestUserInfo()" >사용자 정보 가져오기</button>
</div>




</body>
</html>