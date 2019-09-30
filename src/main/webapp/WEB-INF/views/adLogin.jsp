<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<style>
*:focus {
	outline: none;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="common/css/wlogin.css" rel="stylesheet">
<!-- ajax를 위한 CDN 방식 라이브러리 -->
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {

		$('#loginBtn')
				.click(
						function() {
							var advertisement_email = $('#advertisement_email')
									.val();
							var pw = $('#inputPassword').val();
							var remember_us = $('#remember_us').is(':checked');
							$
									.ajax({

										async : true,
										type : 'POST',
										url : "/adLogincon",
										data : {
											advertisement_email : advertisement_email,
											password : pw,
											remember_userId : remember_us
										},
										success : function(result) {
											if (result == 0) { //로그인 실패시
												$("#spanLoginCheck").text(
														"로그인 정보를 정확히 입력해주세요.");

											} else if (result == -2) { //인증하지 않았다면?
												console.log(result);
												$('#spanLoginCheck').text(
														'이메일 인증을 해주셔야 합니다!');
											} else if (result == -3) { // 아이디가 사용중이라면?
												location.href = '${pageContext.request.contextPath}/user/redundant?advertisement_email='
														+ advertisement_email
														+ '&password='
														+ password
														+ '&remember_userId='
														+ remember_us;
											} else { //로그인 성공시
												location.href = '${pageContext.request.contextPath}/loginss'; //컨트롤러를 호출해서 화면전환
											}
										}
									});
						});
	});
</script>
</head>
<body>
	<!-- Cookie가 비어있지 않을 때 checked 속성을 줌 -->
	<c:if test="${not empty cookie.user_check}">
		<c:set value="checked" var="checked" />
	</c:if>
	<div class="login_wrap">
		<div id="logoBack" class="login_title">
			<a href="/"><img src="images/Bobbing-logo.png" alt="밥빙 logo" /></a>
		</div>
		<div class="w3-content w3-container w3-margin-top w3-center">

			<div class="w3-container w3-card-4">
				<div class="w3-center w3-large w3-margin-top floatClear">
					<div id="layer_11" class="tab">
						<a href="/login"><img src="images/layer_11.png" /></a>
					</div>
					<div id="layer_12" class="tab">
						<a href="/adLogin"><img src="images/layer_12.png" /></a>
					</div>
					<div id="Shape740copy2">
						<img src="images/Shape740copy2.png">
					</div>
					<div id="Shape740copy">
						<img src="images/Shape740copy.png">
					</div>
				</div>
				<div>
					<div class="loginForm">
						<form id="login" method="post">
							<!-- <form id="login" method="post"> -->

							<!-- 세션에 저장한 카카오 아이디를 가져옴 -->
							<input type="hidden" name="kakao_id" value="${kakao_id}" />
							<p class="email">
								<input type="text" class="w3-input" name="advertisement_email"
									id="advertisement_email" placeholder="이메일(example@naver.com)"
									value="${cookie.user_check.value}" required="required">
								<!-- 쿠기에 저장된 벨류(아이디값)을 꺼내옵니다. 서비스에서 쿠키지정 ->컨르롤러로 벨류 전달 -->
								<span id="spanLoginCheck" class="w3-text-red"></span>
							</p>
							<p class="pw">
								<input type="password" class="w3-input" name="password"
									id="inputPassword" placeholder="비밀번호" required="required">
								<span id="spanLoginCheck" class="w3-text-red"></span>
							</p>
							<p class="btn_remember">
								<label class="font-weight-bold text-white"><input
									type="checkbox" id="remember_us" name="remember_userId"
									${checked}> 이메일 저장하기 <!-- 위에 벨류값에 따라 checked가 실행되거나 안되게 합니다. -->
								</label> <a href="${pageContext.request.contextPath}/adUserSearch">
									비밀번호 찾기</a>
							</p>
							<p>
								<input type="button" id="loginBtn"
									class="w3-button w3-block w3-pink w3-ripple w3-margin-top w3-margin-bottom w3-round"
									value="로그인"><br> <br> <input type="button"
									onclick="location.href='/adjoinForm'"
									class="w3-button w3-block w3-pink w3-ripple w3-margin-top w3-margin-bottom w3-round"
									value="회원가입"></input>
							</p>


						</form>

					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- //login_wrap end -->
</body>
</html>