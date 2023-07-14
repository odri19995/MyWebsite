<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="menu">
	<ul>
	    <li id="logo">${pageTitle}</li>
	    <li><a href="<c:url value='/'/>">홈</a></li>
	    <li><a href="<c:url value='/usr/board/list'/>">게시판</a></li>
		<c:if test="${sessionScope.id == null }">
			<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/login"><span>로그인</span></a></li>
		    <li><a href="<c:url value='/usr/member/join'/>">회원가입</a></li>
		</c:if>
		<c:if test="${sessionScope.id != null  }">
			<li><a class="h-full px-3 flex items-center" href="/usr/logout"><span>로그아웃</span></a></li>
	    	<li><a href="<c:url value='/usr/openai/chatbot'/>">챗봇</a></li>
			<li><a class="fa-solid fa-user" href="/usr/member/myPage"></a></li>
		</c:if>
	</ul> 
</div>
