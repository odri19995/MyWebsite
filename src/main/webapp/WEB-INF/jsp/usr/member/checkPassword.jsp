<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="myPage" />
<%@ include file="../common/head.jsp" %>
<link rel="stylesheet" href="<c:url value='/css/menu.css'/>">

<style>
form {
            width:540px;
            height:300px;
            display : flex;
            flex-direction: column;
            position : absolute;
            top:50%;
            left:50%;
            transform: translate(-50%, -50%) ;
            border: 1px solid rgb(89,117,196);
            border-radius: 10px;
            background-color:white;
            
}
input[type='text'], input[type='password'] {
            width: 300px;
            height: 40px;
            border : 1px solid rgb(89,117,196);
            border-radius:5px;
            padding: 0 10px;
            margin-bottom: 10px;
}
table {
		border-spacing: 30px 15px;		/* border 사이의 간격 , 좌우 30px, 상하 10px*/
		width:500px;
		height:300px;
}

</style>


</head>
<body>
<%@ include file="../common/menu.jsp" %>	
	<section class="text-xl">
		<div class="container mx-auto px-3">
			<form action="doCheckPassword" method="POST">
				<div class="table-box-type-1">
					<table>
						<colgroup>
							<col width="200" />
						</colgroup>
						<tbody>
							<tr>
								<th>로그인 아이디</th>
								<td>${rq.loginedMember.loginId }</td>
							</tr>
							<tr>
								<th>로그인 비밀번호</th>
								<td><input class="input input-bordered w-full max-w-xs" type="text" name="loginPw" placeholder="비밀번호를 입력해주세요"/></td>
							</tr>
							<tr>
								<td colspan="2" align="right"><button class="btn-text-link btn btn-active">확인</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			<div class="btns mt-2 flex justify-end">
				<button class="btn-text-link btn btn-active" type="button" onclick="history.back();">뒤로가기</button>
			</div>
			<c:if test="${sessionScope.kakaologin != null  }">				
			<div class="text-sm text-red-500"> ※카카오톡 로그인 초기 비밀번호는 로그인 아이디와 같습니다.※ </div>
			</c:if>
			</form>
		</div>
	</section>
	
</body>
</html>	