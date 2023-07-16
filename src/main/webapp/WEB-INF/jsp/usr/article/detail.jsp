<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../common/head.jsp" %>
<%@ page import = "java.util.ArrayList" %>
<c:set var="pageTitle" value="chatBot" />
<link rel="stylesheet" href="/css/chatBot.css">
<link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <title>ChatbotHistoryd</title>
<style>
#menu{
	position:fixed;
}
</style>
</head>
<body>
	<section>
<%@ include file="../common/menu.jsp" %>
	<h1>${userMessages[0]}</h1>
		 <div class="wrap">
	        <div class="chat ch1" >
	            <div class="icon"><i class="fa-solid fa-user"></i></div>
	            <div class="textbox">안녕하세요. 반갑습니다.</div>
	        </div>
	    </div>
		<c:forEach var="userMessage" items="${userMessages}">
		  <c:out value="${userMessage}"/>
		</c:forEach>
	

	</section>    
</body>
</html>		