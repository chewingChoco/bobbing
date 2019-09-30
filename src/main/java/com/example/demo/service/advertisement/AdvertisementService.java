package com.example.demo.service.advertisement;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.domain.mypage.Advertisementvo;

public interface AdvertisementService {

	List<Advertisementvo> getAllUserPut(String username);
	List<Object[]> getAdvertisementvoOrderByWeightAvg();

	Page<Advertisementvo> getSearchKeyword(String searchKeyword, Pageable pageable);
	
	public int joinAd(Advertisementvo vo);

	public int ademailCheck(Advertisementvo vo, String advertisement_email);

	public int adnickCheck(Advertisementvo vo, String advertisementname);
	
	List<Advertisementvo> findByAdvertisementName(String advertisementName);
	
	List<Advertisementvo> findAll();
	
	void increasePut(String followerYou);
	void decreasePut(String followerYou);
	
	Advertisementvo findAllByAdvertisementEmail(String advertisement_email);
	List<Advertisementvo> getSearchKeywordSearchpage(String searchKeyword);
}