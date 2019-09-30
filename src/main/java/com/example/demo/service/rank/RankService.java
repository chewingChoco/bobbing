package com.example.demo.service.rank;

import java.util.List;

import com.example.demo.domain.mypage.Uservo;

public interface RankService {
	
	List<Object[]> getAllOrderbyUserscoreDESC();
	
	List<Object[]> findAllbyAdvertisementidOrderbyEvaluationAvg();
	
	List<Uservo> getAllUser();
	
	List<Object> checkFollowing(String followerMe, String followingYou);
	
	void follow(String followerMe, String followingYou);
	
	void unfollow(String followerMe, String followingYou);
	
	List<Object> checkPut(String followerMe, String advertisementName);

	void put(String followerMe, String advertisementName);
	
	void unput(String followerMe, String advertisementName);
}