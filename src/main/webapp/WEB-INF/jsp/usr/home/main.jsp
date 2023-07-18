<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="Home" />
<%@ include file="../common/head.jsp" %>
<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.1.0/kakao.min.js"
  integrity="sha384-dpu02ieKC6NUeKFoGMOKz6102CLEWi9+5RQjWSV0ikYSFFd8M3Wp2reIcquJOemx" crossorigin="anonymous"></script>
<head>
	<meta charset="UTF-8">
    <title>mainPage</title>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/bg.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/main.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/> 


</head>
<body>
<%@ include file="../common/menu.jsp" %>
<section>
<!-- 배경 자리 -->
</section>
	<div class = "hero">
		<h2>USE EASY CHATBOT</h2>
		<p>간편한 대화 부터 간단한 설정까지,<br> 당신의 소중한 대화를 간직하세요. </p>
		<button> Chat GPT </button>
	</div>



	


<%@ include file="../common/foot.jsp" %>
