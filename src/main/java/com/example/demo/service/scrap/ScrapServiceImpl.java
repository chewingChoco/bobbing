package com.example.demo.service.scrap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ScrapServiceImpl implements ScrapService   {

	@Autowired
	ScrapRepository scrapRepo;
	
	@Override
	public List<Object> checkScrap(Integer reviewId, String you){
		return scrapRepo.checkScrap(reviewId, you);
	}
	
	@Override
	public void insertScrap(Integer reviewId, String you) {
		scrapRepo.insertScrap(reviewId, you);
	}
	
	@Override
	public void deleteScrap(Integer reviewId, String you) {
		scrapRepo.deleteScrap(reviewId, you);
	}
	
}
