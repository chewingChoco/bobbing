package com.example.demo.service.campaign;

import java.util.List;

import com.example.demo.domain.campaign.CampaignImgvo;
import com.example.demo.domain.campaign.Campaignvo;
import com.example.demo.domain.mypage.Advertisementvo;

public interface CampaignService {

	
	// 모든 캠페인 호출
	List<Campaignvo> getCampaignList(Campaignvo campaignvo);	
	// 최신순 정렬 
	List<Campaignvo> getListByActive(Campaignvo campaignvo);
	// 마감 임박순 정렬
	List<Campaignvo> getListByEndDate(Campaignvo campaignvo);
	// 인기순 정렬
	List<Campaignvo> getListByPopular(Campaignvo campaignvo);
	
	List<Object[]> getListByActiveWithImg(Campaignvo campaignvo);
	List<Object[]> getListByEndDateWithImg(Campaignvo campaignvo);
	List<Object[]> getListByPopularWithImg(Campaignvo campaignvo);	
	
	// 캠페인 작성
 	void insertCampaign(Campaignvo campaignvo);
	
 	// 캠페인 조회
 	Campaignvo getCampaign(Campaignvo campaignvo);
	
 	// 캠페인 수정
 	void updateCampaign(Campaignvo campaignvo);
	
 	// 캠페인 삭제
	void deleteCampaign(Campaignvo campaignvo);
	
	// 참여자수 증가
	void participantCountUp(Campaignvo campaignvo);
	
	// 참여자수 감소
	void participantCountDown(Campaignvo campaignvo);
	
	int selectCampaignId();
	
	void inserCampaignImg(CampaignImgvo campaignImgvo);
	
	List<CampaignImgvo> selectCampaignImgList(int campaignId);
	
	List<Object[]> getCampaignSearchKeyword(String searchKeyword);
	

}