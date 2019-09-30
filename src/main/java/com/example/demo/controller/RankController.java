package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.mypage.Advertisementvo;
import com.example.demo.domain.mypage.Uservo;
import com.example.demo.service.advertisement.AdvertisementService;
import com.example.demo.service.rank.RankService;
import com.example.demo.service.user.UserService;
import com.example.demo.utils.CheckingPut;
import com.example.demo.utils.CheckingRanking;

@Controller
public class RankController {
	@Autowired
	RankService rankService;
	@Autowired
	UserService userService;
	@Autowired
	AdvertisementService advService;
	@RequestMapping("/rank")
	public String goToRanking(Model model, String followerMe) {
		List<CheckingRanking> checkingList = new ArrayList();
		List<CheckingPut> checkingPutList = new ArrayList();
		List<Uservo> userList = rankService.getAllUser();
		List<Advertisementvo> advList = advService.findAll();
		for(int i = 0; i < userList.size(); i++) {
			CheckingRanking checkingRanking = new CheckingRanking();
			checkingRanking.setUservo(userList.get(i));
			checkingRanking.setFollowvo(rankService.checkFollowing(followerMe, userList.get(i).getNickname()));
			checkingList.add(checkingRanking);
		}
		for(int i = 0; i < advList.size(); i++) {
			CheckingPut checkingPut = new CheckingPut();
			checkingPut.setAdvertisementvo(advList.get(i));
			checkingPut.setPutvo(rankService.checkPut(followerMe, advList.get(i).getAdvertisementname()));
			checkingPutList.add(checkingPut);
		}
		model.addAttribute("followerMe", followerMe);
		model.addAttribute("user", checkingList);
		model.addAttribute("adv", checkingPutList);
		System.out.println(userList);
		System.out.println(advList);
		return "th/rank/sub";
	}
	
	@ResponseBody
	@RequestMapping(value = "ajaxfollow", method=RequestMethod.POST)
	public int ajaxfollow(@RequestParam("followerMe") String followerMe, @RequestParam("followingYou") String followingYou, Model model) {
		System.out.println("followerme" + followerMe);
		System.out.println("followingyou" + followingYou);
		int result;
		System.out.println(followerMe.compareTo(followingYou));
		System.out.println(followerMe.isEmpty());
		if(followerMe.isEmpty()==true) {
			result = -1;
		}
		else if(followerMe.compareTo(followingYou) == 0) {
			result = 0;
		} else if(rankService.checkFollowing(followerMe, followingYou).isEmpty()) {
			rankService.follow(followerMe, followingYou);
			userService.followingCountIncrease(followingYou);
			result = 1;
		} else {
			System.out.println("unfollow execution");
			rankService.unfollow(followerMe, followingYou);
			userService.followingCountDecrease(followingYou);
			result = 2;
		}
		model.addAttribute("clickedUser", userService.findByNickname(followingYou));
		System.out.println(userService.findByNickname(followingYou));
		System.out.println(result);
//		model.addAttribute("uservo", rankService.checkFollowing(followerMe, followingYou));
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "ajaxput", method=RequestMethod.POST)
	public int ajaxput(String followerMe, String followingYou, Model model) {
		System.out.println("followerMe" + followerMe);
		System.out.println("followingYou" + followingYou);
		int result;
		if(followerMe.isEmpty()==true) {
			result = -1;
		} else if(followerMe.compareTo(followingYou) == 0) {
			result = 0;
		} else if(rankService.checkPut(followerMe, followingYou).isEmpty()) {
			rankService.put(followerMe, followingYou);
			advService.increasePut(followingYou);
			result = 1;
		} else {
			rankService.unput(followerMe, followingYou);
			advService.decreasePut(followingYou);
			result = 2;
		}
		
		System.out.println(result);
		return result;
	}
}
