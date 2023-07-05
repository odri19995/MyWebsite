<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="boardpage" />
<%@ include file="../common/head.jsp" %>
    

<link rel="stylesheet" href="<c:url value='/css/main.css'/>">
<link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
</head>
<style>
section>div>table{
border : 2px solid black;
background-color: white;
}


</style>


<body>
<%@ include file="../common/menu.jsp" %>
<section>
			<div class="table-box-type-1">
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>날짜</th>
							<th>메세지</th>
							<th>응답</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="article" items="${articles }">
							<tr>
								<td>${article.id }</td>
								<td>${article.regDate.substring(2, 16) }</td>
								<td>${article.userMessage }</td>
								<td>${article.response }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
 </section>			
</body>
</html>