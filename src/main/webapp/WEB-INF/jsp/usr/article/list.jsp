<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="ChatList" />
<%@ include file="../common/head.jsp" %>
    

<link rel="stylesheet" href="<c:url value='/css/main.css'/>">
<link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
</head>



<body>
<%@ include file="../common/menu.jsp" %>
	<section class="mt-32 text-xl">
		<div class="container mx-auto px-3">
				<div class="table-box-type-1">
					<table class="table w-full">
						<colgroup>
							<col width="60"/>
							<col width="200"/>
							<col />
							<col width="120"/>
						</colgroup>
						<thead>
							<tr>
								<th>번호</th>
								<th>날짜</th>
								<th>제목</th>
								<th>작성자</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="article" items="${articles }">
								<tr class="hover">
									<td>${article.id }</td>
									<td>${article.regDate.substring(2, 16) }</td>
									<td><a class="hover:underline" href="detail?id=${article.id }">${article.title }</a></td>
									<td>${article.writerName }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
 	</section>			
</body>
</html>