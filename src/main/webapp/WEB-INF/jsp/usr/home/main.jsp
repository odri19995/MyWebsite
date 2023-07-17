<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="Home" />
<%@ include file="../common/head.jsp" %>
<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.1.0/kakao.min.js"
  integrity="sha384-dpu02ieKC6NUeKFoGMOKz6102CLEWi9+5RQjWSV0ikYSFFd8M3Wp2reIcquJOemx" crossorigin="anonymous"></script>
<head>
	<meta charset="UTF-8">
    <title>mainPage</title>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/bg.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/main.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/> 


</head>
<body>
<%@ include file="../common/menu.jsp" %>
	<section> 배경
			<figure class="snip1504 hover"><img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/331810/sample59.jpg" alt="sample59" />
 			 <figcaption>
   				 <h3>Sheq Bivouac</h3>
  			  	<h5>Branding</h5>
  			</figcaption>
 			 <a href="#"></a>
			</figure>
	</section>
<script>
$(".hover").mouseleave(
  function() {
    $(this).removeClass("hover");
  }
);
</script>	



<%@ include file="../common/foot.jsp" %>
