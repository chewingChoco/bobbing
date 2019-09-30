
$(function(){
	
	var list = document.getElementsByClassName('temp');
	for ( var i = 0; i < list.length; i ++){
		list[i].setAttribute('id', "box" + i);
	}
	

	$('.emptyspace').on('click', function(){
		var followerMe = $('#followerMe').val();
		var followingYou = $(this).children("#followingYou").val();
		var closestATTR = $(this).find('.followbtn');
		
		$.ajax({
			url:"ajaxfollow",
			dataType: "JSON",
			type: "POST",
			data: {followerMe : followerMe, followingYou : followingYou},
			
			success:function(result){
				if(result == 1){
					var near = $(closestATTR).closest('.followbtn');
					$(near).removeClass('btn02');
					$(near).addClass('btn01');
					$(near).empty();
					$(near).text('팔로잉');
					
				} else if(result == 2) {
					var near = $(closestATTR).closest('.followbtn');
					$(near).removeClass('btn01');
					$(near).addClass('btn02');
					$(near).empty();
					$(near).text('팔로우');
				} else if(result == 0) {
					alert("자기자신을 팔로우 할 수 없습니다");
				} else if(result == -1) {
					alert("로그인을 해주세요");
				}
				
			}
		});
		
	});
	$('.emptyspace2').on('click', function(){
		var followerMe = $('#followerMe').val();
		var followingYou = $(this).children().closest("#followingYou").val();
		var closestATTR = $(this).find('.followbtn');
		
;		$.ajax({
			url:"ajaxput",
			dataType: "JSON",
			type: "POST",
			data: {followerMe : followerMe, followingYou : followingYou},
			
			success:function(result){
				if(result == 1){
					var near = $(closestATTR).closest('.followbtn');
					$(near).removeClass('btn02');
					$(near).addClass('btn01');
					$(near).empty();
					$(near).text('담기완료');
					
				} else if(result == 2) {
					var near = $(closestATTR).closest('.followbtn');
					$(near).removeClass('btn01');
					$(near).addClass('btn02');
					$(near).empty();
					$(near).text('담아두기');
				} else if(result == 0) {
					alert("자기자신을 팔로우 할 수 없습니다");
				} else if(result == -1) {
					alert("로그인을 해주세요");
				}
				
			}
		});
		
	});
	
	//업종 버튼
	$('.buttons').on('click', function(){
		$('.buttons.on').removeClass('on');
		$(this).closest('.buttons').addClass('on');
		var type = $(this).closest('.buttons').children().attr('id');
		var current = $(this);
		var followerMe = $(this).parent().parent().siblings('#followerMe').val();
		
		$.ajax({
			url: "tabMenu",
			dataType: "JSON",
			type: "GET",
			data: {type : type, followerMe : followerMe},
			
			success:function(testing){
				
				var insideDiv = $(current).parent().parent().siblings().closest(".cont-area.info");
				
				for(i in testing){

					var box = $(insideDiv).children().find('#box'+i);
					
					//리뷰의 이미지
					$(box).children().children('.photos').find("#img1").attr("src", testing[i].img[i][4]);
					//리뷰의 북마크 버튼 여부
					if(testing[i].scrapvo!=''){
						
						$(box).children().children().find('#scrapButton').addClass('best01');
						$(box).children().children().find('#scrapButton').removeClass('best02');
					}
					if(testing[i].scrapvo==''){
					
						$(box).children().children().find('#scrapButton').addClass('best02');
						$(box).children().children().find('#scrapButton').removeClass('best01');
					}
					
					//리뷰의 장소
					$(box).children().children('.info').children().find('#reviewPlace').html(testing[i].img[i][1]);
					//리뷰의 타이틀
					$(box).children().children('.info').children().eq(2).find('#reviewTitle').html(testing[i].img[i][2]);
					//리뷰의 프로파일 이미지
					$(box).children().children('.info').children().eq(3).children().find('#img2').attr('src', testing[i].img[i][5]);
					//리뷰의 프로파일 네임
					$(box).children().children('.info').children().eq(3).children().find('#userNickname').html(testing[i].img[i][6]);
					//스크랩을위한 리뷰번호 번경
					$(box).children().children('.info').find('#get').val(testing[i].img[i][0]);
					//스크랩을위한 리뷰작성자 아이디 변경
					$(box).children().children('.info').children().eq(3).children().children('#reviewUserId').val(testing[i].img[i][7]);
				}
				
			}
		});
	});
	//스크랩 버튼 이름 죄송
	$('.button').on('click', function(){
		var button = $(this);
		var followerMe = $('#followerMe').val();
		var reviewUserId = $(this).parent().siblings().children().children().children().closest('#reviewUserId').val();
		var reviewId = $(this).parent().siblings().children().closest('#get').val();
		console.log(followerMe);
		console.log("reviewUserId" + reviewUserId);
		console.log(reviewId);
		$.ajax({
			url: "scrapButton",
			dataType: "JSON",
			type: "GET",
			data: {followerMe : followerMe, reviewUserId : reviewUserId, reviewId : reviewId},
			success:function(result){
				if(result == -1){
					alert("로그인을 해주세요");
				} else if(result == 0){
					$(button).removeClass();
					$(button).addClass('best01');
				} else if(result == 1){
					$(button).removeClass();
					$(button).addClass('best02');
				}
				
				
			}
			
		});
		
		
	});
	
});