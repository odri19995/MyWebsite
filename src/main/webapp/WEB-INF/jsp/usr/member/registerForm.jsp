<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder"%>
<%@ include file="../common/head.jsp" %>
    <link rel="stylesheet" href="/css/registerForm.css" />
    <link rel="stylesheet" href="<c:url value='/css/bg.css'/>">
    <style>
* {
	margin:0;
	padding:0;
            
}
    </style>
    
    <title>Register</title>    
</head>
<body>
<section>배경</section>
   <form action="<c:url value="/usr/member/doJoin"/>" method="post" onsubmit="return formCheck(this)">
    <div class="title">Register</div>
    <div id="msg" class="msg">
   	    <c:if test="${not empty param.msg}">
	        <i class="fa fa-exclamation-circle"> ${URLDecoder.decode(param.msg)}</i>            
	    </c:if>
    </div> 
    <label for="">아이디</label>
    <input class="input-field" type="text" name="loginId" placeholder="8~12자리의 영대소문자와 숫자 조합">
    <label for="">비밀번호</label>
    <input class="input-field" type="text" name="loginPw" placeholder="8~12자리의 영대소문자와 숫자 조합">
    <label for="">이름</label>
    <input class="input-field" type="text" name="name" placeholder="홍길동">
    <label for="">닉네임</label>
    <input class="input-field" type="text" name="nickname" placeholder="닉네임">
    <label for="">이메일</label>
    <input class="input-field" type="text" name="email" placeholder="example@gmail.co.kr"> 

    <button>회원 가입</button>
    <button class="btn-text-link btn btn-active" type="button" onclick="history.back();">뒤로가기</button> 
   </form>

   <script>
       function formCheck(frm) {
            let msg ='';

            if(frm.id.value.length<3) {
                setMessage('id의 길이는 3이상이어야 합니다.', frm.id);
                return false;
            }

            if(frm.pwd.value.length<3) {
                setMessage('pwd의 길이는 3이상이어야 합니다.', frm.pwd);
                return false;
            }           
           
           return true;
       }

       function setMessage(msg, element){
            document.getElementById("msg").innerHTML = `<i class="fa fa-exclamation-circle"> ${'${msg}'}</i>`;

            if(element) {
                element.select();
            }
       }
   </script>
</body>
</html>