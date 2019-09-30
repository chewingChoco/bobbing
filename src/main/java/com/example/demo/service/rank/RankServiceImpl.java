package com.example.demo.service.rank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.mypage.Uservo;
import com.example.demo.service.advertisement.AdvertisementRepository;
import com.example.demo.service.put.PutRepository;
import com.example.demo.service.user.UserRepository;

@Service
public class RankServiceImpl implements RankService {
//	@Autowired
//	RankRepository rankRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	AdvertisementRepository advertisementRepo;
	@Autowired
	RankRepository rankRepo;
	@Autowired
	PutRepository putRepo;
	
	public List<Object[]> getAllOrderbyUserscoreDESC(){
		return userRepo.getAllOrderbyUserscoreDESC();
	}
	
	public List<Object[]> findAllbyAdvertisementidOrderbyEvaluationAvg(){
		return advertisementRepo.findAllbyAdvertisementidOrderbyEvaluationAvg();
	}
	
	public List<Uservo> getAllUser(){
		return (List<Uservo>) userRepo.findAll();
	}
	
	public List<Object> checkFollowing(String followerMe, String followingYou){
		return rankRepo.checkFollowing(followerMe, followingYou);
	}
	
	public void follow(String followerMe, String followingYou) {
		rankRepo.follow(followerMe, followingYou);
	}
	
	public void unfollow(String followerMe, String followingYou) {
		rankRepo.unfollow(followerMe, followingYou);
	}
	
	public List<Object> checkPut(String followerMe, String advertisementName){
		return putRepo.checkPut(followerMe, advertisementName);
	}
	
	public void put(String followerMe, String advertisementName) {
		putRepo.put(followerMe, advertisementName);
	}
	
	public void unput(String followerMe, String advertisementName) {
		putRepo.unput(followerMe, advertisementName);
	}
}
