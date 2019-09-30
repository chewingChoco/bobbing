
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>로그인성공메인</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<script type="text/javascript">
	$(document).ready(function() {
		var email = ${result}.response.email;
		$("#email").html("환영합니다. "+email+"님");
	  });
</script>
</head>
<body>
	<div class="w3-content w3-container w3-margin-top">
		<div class="w3-container w3-card-4">
			<div class="w3-center w3-large w3-margin-top">

				<a><img
					src="https://www.dropbox.com/s/vt6mcuhmpo9vxio/Bobbing-logo.png?dl=1"
					style="height: 80px; margin-left: 10px;" /></a> <br> <br> <br>
				<br>
				<h3><%=session.getAttribute("signedUser")%>님 <small>반갑습니다.</small>
				</h3>
				<p>밥빙에서 새로운 체험의 기회와 경험을 나누세요!</p>
				<br> <br>
				<h3>
					${sessionScope.signedUser } 님! <small>시간이 얼마 없어요 :)</small>
				</h3>
				<br> <a
					class="w3-button w3-block w3-pink w3-ripple w3-margin-top w3-round"
					href="${pageContext.request.contextPath}/">HOME</a> <a
					class="w3-button w3-block w3-pink w3-ripple w3-margin-top w3-round"
					href="logout">LOGOUT</a>


			</div>
		</div>
	</div>
</body>
</html>