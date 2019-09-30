<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
*:focus {
	outline: none;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>joinForm</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="common/css/wlogin.css" rel="stylesheet">
<!-- ajax를 위한 CDN 방식 라이브러리 -->
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#joinForm").submit(
				function() {
					if ($("#pw").val() !== $("#pw2").val()) {
						alert("비밀번호가 다릅니다.");
						$("#pw").val("").focus();
						$("#pw2").val("");
						return false;
					} else if ($("#pw").val().length < 8) {
						alert("비밀번호는 8자 이상으로 설정해야 합니다.");
						$("#pw").val("").focus();
						$("#pw2").val("");
						return false;
					} else if ($.trim($("#pw").val()) !== $("#pw").val()
							|| $.trim($("#nickname").val()) !== $("#nickname")
									.val()
							|| $.trim($("#user_email").val()) !== $(
									"#user_email").val()) {
						alert("공백은 입력이 불가능합니다.");
						return false;
					}

				})

		/* 이메일 체크 */

		//input에 입력된 값이 변화가 있을때 alert 을 띄운다.
		$("input[name='user_email']").on("change", function() {
			var user_email = $('#user_email').val();
			var userInfo = {"user_email" : user_email};
			$.ajax({
				async : true,
				type : 'POST',
				data : userInfo,
				url : "/emailCheck",
				contentType : "application/json; charset=UTF-8",
				success : function(cnt) {
					if (user_email == "") {
						$("#chktext").css("color", "gray");
						$("#chktext").text("이메일을 입력해주세요.");

						$("#chktext2").html("");
					} else if (cnt == 0) {
						$("#chktext2").css("color", "blue");
						$("#chktext2").text("사용 가능한 이메일 입니다.");
						$("#chktext").html("");
						$("#joinBtn").removeAttr("disabled");
					} else {
						$("chktext").css("color", "red");
						$("#chktext").text("이미 사용중인 이메일 입니다.");
						$("#chktext2").html("");
						$("#joinBtn").attr("disabled", "disabled");

					}
				}
			});
		});
	});

	/*닉네임 체크  */

	$(function() {
		//input에 입력된 값이 변화가 있을때 alert 을 띄운다.
		$("input[name='nickname']").on("change", function() {
			var nickname = $('#nickname').val();
			var userInfo = {"nickname" : nickname};
			//ajax 호출

			$.ajax({
				async : true,
				type : 'POST',
				data : userInfo,
				url : "/nickCheck",
				//controller에서 성공적으로 return 받을시 실행되는 메소드를 입력합니다.
				contentType : "application/json; charset=UTF-8",
				success : function(cnt) {
					if (nickname == "") {
						$("#chktext").css("color", "gray");
						$("#nickchktext").text("닉네임을 입력해주세요.");

						$("#nickchktext2").html("");
					} else if (cnt == 0) {
						$("#nickchktext2").css("color", "blue");
						$("#nickchktext2").text("사용 가능한 닉네임 입니다.");
						$("#nickchktext").html("");
						$("#joinBtn").removeAttr("disabled");
					} else {
						$("#nickchktext").css("color", "red");
						$("#nickchktext").text("이미 사용중인 닉네임 입니다.");
						$("#nickchktext2").html("");
						$("#joinBtn").attr("disabled", "disabled");

					}

				},
				//에러 발생 시 실행되는 메소드
				error : function(error) {

					alert("error :" + error);

				}
			});
		});
	});
</script>

</head>
<body>
	<div class="login_wrap">
		<div id="logoBack" class="login_title">
			<a href="/"><img src="images/Bobbing-logo.png" alt="밥빙 logo" /></a>
		</div>
		<div class="w3-content w3-container w3-margin-top w3-center">

			<div class="w3-container w3-card-4">
				<div class="w3-center w3-large w3-margin-top floatClear">
					<div id="layer_11" class="tab">
						<a href="/joinTeb"><img src="images/layer_11.png" /></a>
					</div>
					<div id="layer_12" class="tab">
						<a href="/adJoinTeb"><img src="images/layer_12.png" /></a>
					</div>
					<div id="Shape740copy2">
						<img src="images/Shape740copy.png">
					</div>
					<div id="Shape740copy">
						<img src="images/Shape740copy2.png">
					</div>
				</div>
				<div class="loginForm">
					<form id="joinForm" action="/join" method="post">
						<!-- 세션에 저장한 카카오 아이디를 가져옴 -->
						<input type="hidden" name="kakao_id" value="${kakao_id}" />

						<p class="email">
							<input type="text" class="w3-input" name="user_email"
								id="user_email" placeholder="이메일(example@naver.com)"
								required="required"> <span id="chktext"
								class="w3-text-red"></span> <span id="chktext2"
								class="w3-text-blue"></span>
						</p>

						<p class="pww">
							<input type="text" class="w3-input" name="nickname" id="nickname"
								placeholder="닉네임" required="required"> <span
								id="nickchktext" class="w3-text-red"></span> <span
								id="nickchktext2" class="w3-text-blue"></span>
						</p>
						<p class="pw">
							<input type="password" class="w3-input" name="password" id="pw"
								placeholder="비밀번호(8자리 이상)" required="required">
						</p>
						<p>
							 <input type="password" class="w3-input" id="pw2"
								placeholder="비밀번호 확인" required="required">
						</p>

						<p class="btn_sns">
							 <a href="${url}"> <img
								src="${pageContext.request.contextPath}/images/네아로.png"
								align="middle" height="50" width="80%" />
							</a> <a href="#"> <img
								src="${pageContext.request.contextPath}/images/페아로.png"
								width="80%" height="50" class="  w3-margin-top " />
							</a>
							<button type="submit" id="joinBtn"
								class="w3-button w3-block w3-pink w3-ripple w3-margin-top w3-round">회원가입</button>
						</p>
					</form>

				</div>
			</div>
		</div>
	</div>
</body>
</html>