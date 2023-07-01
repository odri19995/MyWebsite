<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<!-- 제이쿼리 불러오기 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.1.0/kakao.min.js"
  integrity="sha384-dpu02ieKC6NUeKFoGMOKz6102CLEWi9+5RQjWSV0ikYSFFd8M3Wp2reIcquJOemx" crossorigin="anonymous"></script>
<head>
	<meta charset="UTF-8">
    <title>mainPage</title>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/> 

<style>
body { background-size:100% 100%;;
		background:url('/images/bg1.jpg') no-repeat left top;
		background-position: 0px 50%					
}
</style>


</head>
<body>
<div id="menu">
	<ul>
	    <li id="logo">mainpage</li>
	    <li><a href="<c:url value='/'/>">홈</a></li>
	    <li><a href="<c:url value='/usr/board/list'/>">게시판</a></li>
		<c:if test="${sessionScope.id == null }">
			<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/login"><span>로그인</span></a></li>
		    <li><a href="<c:url value='/usr/member/join'/>">회원가입</a></li>
		</c:if>
		<c:if test="${sessionScope.id != null  }">
			<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/logout"><span>로그아웃</span></a></li>
	    	<li><a href="<c:url value='/usr/openai/chatbot'/>">챗봇</a></li>
		</c:if>
	    
	    <li><a href=""><i class="fas fa-search small"></i></a></li>
	</ul> 
</div>
<div id ="bg1" style="text-align:center">
	<h1>This is HOME</h1>
	<h1>This is HOME</h1>
	<h1>This is HOME</h1>
</div>




</body>
</html>