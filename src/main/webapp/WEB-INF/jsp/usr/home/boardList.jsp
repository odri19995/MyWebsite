<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="boardpage" />
<%@ include file="../common/head.jsp" %>
    

<link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
</head>
<body>
<div id="menu">
	<ul>
	    <li id="logo">boardpage</li>
	    <li><a href="<c:url value='/'/>">Home</a></li>
	    <li><a href="<c:url value='/usr/board/list'/>">Board</a></li>
		<c:if test="${sessionScope.id == null }">
			<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/login"><span>login</span></a></li>
		</c:if>
		<c:if test="${sessionScope.id != null  }">
			<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/logout"><span>logout</span></a></li>
		</c:if>
	    <li><a href="<c:url value='/usr/member/join'/>">Sign in</a></li>
	    <li><a href=""><i class="fas fa-search small"></i></a></li>
	</ul> 
</div>
<div style="text-align:center">
	<h1>This is BOARD</h1>
	<h1>This is BOARD</h1>
	<h1>This is BOARD</h1>
	<h1>This is BOARD</h1>
	<h1>This is BOARD</h1>
</div>
</body>
</html>