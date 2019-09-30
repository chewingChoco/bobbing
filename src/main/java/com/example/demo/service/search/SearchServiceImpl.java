package com.example.demo.service.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.service.advertisement.AdvertisementRepository;
import com.example.demo.service.campaign.CampaignRepository;
import com.example.demo.service.review.ReviewRepository;
import com.example.demo.service.user.UserRepository;

@Service
public class SearchServiceImpl {
	
	@Autowired
	AdvertisementRepository advertisementRepo;
	@Autowired
	CampaignRepository campaignRepo;
	@Autowired
	ReviewRepository reviewRepo;
	@Autowired
	UserRepository userRepo;
	
	
}
