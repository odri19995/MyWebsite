<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage= "true"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>error.jsp</title>
</head>
<body>
<h1>예외가 발생했습니다.</h1>
<!-- 모델에 주지않아도 exception을 가져올 수 있다. -->
발생한 예외 : ${pageContext.exception}<br>
예외 메시지 : ${ex.message}<br>
<ol>
<c:forEach items="${ex.stackTrace}" var="i">
	<li>${i.toString()}</li>
</c:forEach>
</ol>
</body>
</html>
