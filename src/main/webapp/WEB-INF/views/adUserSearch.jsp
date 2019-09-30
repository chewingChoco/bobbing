<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<style>
*:focus {
	outline: none;
}
</style>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="common/css/wlogin.css" rel="stylesheet">

<!-- ajax를 위한 CDN 방식 라이브러리 -->
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#searchBtn2").submit(function() {
			if ($.trim($("#advertisement_email").val()) !== $("#advertisement_email").val()) {
				alert("아이디를 입력해주세요");
				return false;
			}

		})
		//input에 입력된 값이 변화가 있을때 alert 을 띄운다.
		$("input[name='advertisement_email']").on("change", function() {
			var advertisement_email = $('#advertisement_email').val();

			//ajax 호출

			$.ajax({
				//Default datatype : JS ON
				async : true,
				type : 'POST',
				data : JSON.stringify({
					// 좌항 - 변수 , 우항 - 입력된 데이터 의미
					'advertisement_email' : advertisement_email
				}),
				//요청 url
				url : "/ademailCheck",
				//controller에서 성공적으로 return 받을시 실행되는 메소드를 입력합니다.
				dataType : "json",
				contentType : "application/json; charset=UTF-8",
				success : function(cnt) {
					if (advertisement_email == "") {
						$("#chktext").css("color", "gray");
						$("#chktext").text("이메일을 입력해주세요.");

						$("#chktext2").html("");
					} else if (cnt == 0) {
						$("chktext").css("color", "red");
						$("#chktext").text("회원가입을 먼저 진행해주세요!");
						$("#chktext2").html("");
						$("#joinBtn").attr("disabled", "disabled");
					} else {
						$("#chktext2").css("color", "blue");
						$("#chktext2").text("회원님 비밀번호 변경 가능합니다!");
						$("#chktext").html("");
						$("#joinBtn").removeAttr("disabled");

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
						<a href="/userSearch"><img src="images/layer_11.png" /></a>
					</div>
					<div id="layer_12" class="tab">
						<a href="/adUserSearch"><img src="images/layer_12.png" /></a>
					</div>
					<div id="Shape740copy2">
						<img src="images/Shape740copy2.png">
					</div>
					<div id="Shape740copy">
						<img src="images/Shape740copy.png">
					</div>
				</div>
				<div class="loginForm">
					

				<form action="adUser/searchPassword" id="searchBtn2" method="post">
					<br> <br>
					<h4>비밀번호를 잃어버리셨나요?</h4>
					<p id="pwInfo">
						회원 가입시 사용한 이메일 주소를 입력하시면<br> 비밀번호 재설정 안내 메일을 보내드립니다.
					</p><br> <br>
					<p class="email">
						<input type="text" class="w3-input" name="advertisement_email"
							id="advertisement_email" placeholder="example@naver.com"
							required="required">
						<!-- 쿠기에 저장된 벨류(아이디값)을 꺼내옵니다. 서비스에서 쿠키지정 ->컨르롤러로 벨류 전달 -->
						<span id="chktext" class="w3-text-red"></span> <span id="chktext2"
							class="w3-text-blue"></span>

					</p>
					<button type="submit" id="joinBtn"
						class="w3-button w3-block w3-pink w3-ripple w3-margin-top w3-round">안내
						메일전송</button>


				</form>

			</div>
		</div>
	</div>
	</div>
</body>
</html>