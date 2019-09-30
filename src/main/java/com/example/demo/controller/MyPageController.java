package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.domain.mypage.Advertisementvo;
import com.example.demo.domain.mypage.Uservo;
import com.example.demo.domain.review.ReviewRegistrationvo;
import com.example.demo.service.advertisement.AdvertisementService;
import com.example.demo.service.campaign.CampaignService;
import com.example.demo.service.rank.RankService;
import com.example.demo.service.review.ReviewService;
import com.example.demo.service.scrap.ScrapService;
import com.example.demo.service.user.UserService;
import com.example.demo.utils.CheckingPut;
import com.example.demo.utils.CheckingRanking;
import com.example.demo.utils.CheckingScrap;

@SessionAttributes("uservo")
@Controller
public class MyPageController {
	
	@Autowired
	CampaignService campaignService;
	@Autowired
	UserService userService;
	@Autowired
	RankService rankService;
	@Autowired
	ReviewService reviewService;
	@Autowired
	ScrapService scrapService;
	@Autowired
	AdvertisementService advertiseService;
	
	
	@RequestMapping("/getUserMyPage")
	public String getUserMyPage(Uservo uservo, Model model, String nickname, String followerMe) {
		//유저마이페이지 leftSide 
		model.addAttribute("uservo", userService.findByNickname(nickname));
		model.addAttribute("followCheck", rankService.checkFollowing(followerMe, nickname));
		model.addAttribute("username", followerMe);
		System.out.println(nickname);
		System.out.println(followerMe);
		//자기 페이지는 팔로우 버튼 없음, 대신 수정 버튼 생김
		if(nickname.equals(followerMe)) {
			model.addAttribute("sameuser","its same");
		}
		
		//유저/자기가 작성한 글 
		List<ReviewRegistrationvo> userSelfReviewList = reviewService.getUserSelfWroteReview(userService.findByNickname(nickname).get(0).getUserId());
		List<CheckingScrap> userSelfReviewCheckingScrapList = new ArrayList();
		
		for(int i = 0; i < userSelfReviewList.size(); i++) {
			CheckingScrap checkingScrap = new CheckingScrap();
			checkingScrap.setReviewRegistrationvo(userSelfReviewList.get(i));
			checkingScrap.setScrapvo(scrapService.checkScrap(userSelfReviewList.get(i).getReviewId(), followerMe));
			userSelfReviewCheckingScrapList.add(checkingScrap);
			
			System.out.println(userSelfReviewList.get(1));
			System.out.println(scrapService.checkScrap(userSelfReviewList.get(i).getReviewId(), nickname));
		}
		System.out.println(userSelfReviewCheckingScrapList);
		model.addAttribute("review", userSelfReviewCheckingScrapList);
		
		//유저/자기가 다음 글
		List<ReviewRegistrationvo> userScrapReviewList = reviewService.getScrappedReview(nickname);
		List<CheckingScrap> userScrapReviewScrapList = new ArrayList();
		
		for(int i = 0; i < userScrapReviewList.size(); i++) {
			CheckingScrap checkingScrap = new CheckingScrap();
			checkingScrap.setReviewRegistrationvo(userScrapReviewList.get(i));
			checkingScrap.setScrapvo(scrapService.checkScrap(userScrapReviewList.get(i).getReviewId(), followerMe));
			userScrapReviewScrapList.add(checkingScrap);
		}
		model.addAttribute("scrap", userScrapReviewScrapList);
		
		//해당 유저페이지 유저를 팔로우 한 사람들. 팔로우 버튼 기준은 로그인 한 유저 기준
		List<Uservo> userFollowerList = userService.getAllFollowers(nickname);
		List<CheckingRanking> userFollowerCheckList = new ArrayList();
		
		for(int i = 0; i < userFollowerList.size(); i ++) {
			CheckingRanking checkingRanking = new CheckingRanking();
			checkingRanking.setUservo(userFollowerList.get(i));
			checkingRanking.setFollowvo(rankService.checkFollowing(followerMe, userFollowerList.get(i).getNickname()));
			userFollowerCheckList.add(checkingRanking);
		}
		model.addAttribute("followerUser", userFollowerCheckList);
		
		//해당 유저가 팔로우 하는 사람들... 팔로우 버튼 기준은 당연히 로그인 한 유저 기준!
		List<Uservo> userFollowingList = userService.getAllFollowingUsers(nickname);
		List<CheckingRanking> userFollowingCheckList = new ArrayList();
		
		for(int i = 0; i < userFollowingList.size(); i++) {
			CheckingRanking checkingRanking = new CheckingRanking();
			checkingRanking.setUservo(userFollowingList.get(i));
			checkingRanking.setFollowvo(rankService.checkFollowing(followerMe, userFollowingList.get(i).getNickname()));
			userFollowingCheckList.add(checkingRanking);
		}
		model.addAttribute("followingUser", userFollowingCheckList);
		
		//해당 유저의 담아둔 기업들! 당연히 담아두기 버튼 기준은 로그인 한 유저 입니다!
		List<Advertisementvo> advertisementList = advertiseService.getAllUserPut(nickname);
		List<CheckingPut> advertisementPutCheckList = new ArrayList();
		
		for(int i = 0; i < advertisementList.size(); i++) {
			CheckingPut checkingPut = new CheckingPut();
			checkingPut.setAdvertisementvo(advertisementList.get(i));
			checkingPut.setPutvo(rankService.checkPut(followerMe, advertisementList.get(i).getAdvertisementname()));
			advertisementPutCheckList.add(checkingPut);
		}
		model.addAttribute("advertisement", advertisementPutCheckList);
		
		return "th/mypage/userMyPage";
	}
	
	@RequestMapping("/getAdvertisementMyPage")
	public String getAdvertisementMyPage(Advertisementvo advertisementvo, Model model, String advertisementemail) {		
		
		System.out.println(advertiseService.findAllByAdvertisementEmail(advertisementemail));
		model.addAttribute("advertisement", advertiseService.findAllByAdvertisementEmail(advertisementemail));
		
		return "th/mypage/advertisementMyPage";
	}
}
