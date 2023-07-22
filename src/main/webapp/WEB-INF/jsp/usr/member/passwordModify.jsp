<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../common/head.jsp" %>
<c:set var="pageTitle" value="myPage" />
<link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
</head>
<body>
<%@ include file="../common/menu.jsp" %>	
	<section class="text-xl">
		<div class="container mx-auto px-3 mt-32">
			<form action="doPasswordModify" method="POST">
				<div class="table-box-type-1">
					<table>
						<colgroup>
							<col width="200" />
						</colgroup>
						<tbody>
							<tr>
								<th>새 비밀번호</th>
								<td><input class="input input-bordered w-full max-w-xs" type="text" name="loginPw" placeholder="새 비밀번호를 입력해주세요"/></td>
							</tr>
							<tr>
								<th>새 비밀번호 확인</th>
								<td><input class="input input-bordered w-full max-w-xs" type="text" name="loginPwChk" placeholder="새 비밀번호 확인을 입력해주세요"/></td>
							</tr>
							<tr>
								<td colspan="2"><button class="btn-text-link btn btn-active">변경</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
			<div class="btns mt-2">
				<button class="btn-text-link btn btn-active" type="button" onclick="history.back();">뒤로가기</button>
			</div>
		</div>
	</section>

</body>
</html>