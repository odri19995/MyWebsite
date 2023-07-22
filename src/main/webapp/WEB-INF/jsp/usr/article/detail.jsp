<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="chatDetail" />
<%@ include file="../common/head.jsp" %>
<%@ page import = "java.util.ArrayList" %>
<link rel="stylesheet" href="/css/chatBot.css">
<link rel="stylesheet" href="<c:url value='/css/main.css'/>">
<link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
<style>
#menu{
	position:fixed;
}
</style>
</head>
<body>
	<section>
<%@ include file="../common/menu.jsp" %>
		<div class="mt-28">
			 <div class="wrap">
		        <div class="chat ch1" >
		            <div class="icon"><i class="fa-solid fa-user"></i></div>
		            <div class="textbox">안녕하세요. 반갑습니다.</div>
		        </div>
				<c:forEach items="${userMessages}" var="userMessage" varStatus="status">
				    <div class="chat ch2">
				        <div class="icon"><i class="fa-solid fa-user"></i></div>
				        <div class="textbox">${userMessage}</div>
				    </div>
				    <div class="chat ch1">
				        <div class="icon"><i class="fa-solid fa-user"></i></div>
				        <div class="textbox">${responses[status.index]}</div>
				    </div>
				</c:forEach>
		    </div>
		    
		    <div class="btns mt-2 flex justify-end">
				<button class="btn-text-link btn btn-active m-4" type="button" onclick="history.back();">뒤로가기</button>
				<a class="btn-text-link btn btn-active m-4 mr-20" href="doDelete?id=${param.id }" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a>
			</div>
	    </div>
	</section>    
</body>
</html>		