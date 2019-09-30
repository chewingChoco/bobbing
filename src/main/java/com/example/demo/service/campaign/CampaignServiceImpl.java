package com.example.demo.service.campaign;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.campaign.CampaignImgvo;
import com.example.demo.domain.campaign.Campaignvo;

@Service
public class CampaignServiceImpl implements CampaignService {
   @Autowired
   CampaignRepository campaignRepo;
   @Autowired
   CampaignImgRepository campaignImgRepo;
      
   @Override
   public List<Campaignvo> getCampaignList(Campaignvo campaignvo) {
       
      return (List<Campaignvo>) campaignRepo.findAll();
   }

   @Override
   public List<Campaignvo> getListByActive(Campaignvo campaignvo) {
      // TODO Auto-generated method stub
      return (List<Campaignvo>) campaignRepo.findAllByActive();
   }
   
   @Override
   public List<Campaignvo> getListByEndDate(Campaignvo campaignvo) {
      // 
      return (List<Campaignvo>) campaignRepo.findAllByOrderByEndDateAsc();
   }   

   @Override
   public List<Campaignvo> getListByPopular(Campaignvo campaignvo) {
      
      return (List<Campaignvo>) campaignRepo.findAllByPopularByActive();
   }
   
   @Override
   public void insertCampaign(Campaignvo campaignvo) {
      campaignRepo.save(campaignvo);
      
   }

   @Override
   public Campaignvo getCampaign(Campaignvo campaignvo) {
       
      return campaignRepo.findById(campaignvo.getCampaignId()).get();
   }

   @Override
   public void updateCampaign(Campaignvo campaignvo) {
      Campaignvo findCampaign = campaignRepo.findById(campaignvo.getCampaignId()).get();
      Date modiDate = new Date();
      
      findCampaign.setTitle(campaignvo.getTitle());
      findCampaign.setIntroduction(campaignvo.getIntroduction());
      findCampaign.setRecruitment(campaignvo.getRecruitment());
      findCampaign.setStartDate(campaignvo.getStartDate());
      findCampaign.setEndDate(campaignvo.getEndDate());
      findCampaign.setRemarks(campaignvo.getRemarks());
      findCampaign.setOfferHistory(campaignvo.getOfferHistory());
      findCampaign.setModifiedDate(modiDate);
      
      campaignRepo.save(findCampaign);
   }

   @Override
   public void deleteCampaign(Campaignvo campaignvo) {
      campaignRepo.deleteByCampaignId(campaignvo.getCampaignId());
   }

   @Override
   public void participantCountUp(Campaignvo campaignvo) {
      campaignvo.setParticipants(campaignvo.getParticipants()+1);
      campaignRepo.save(campaignvo);
      
   }

   @Override
   public void participantCountDown(Campaignvo campaignvo) {
      campaignvo.setParticipants(campaignvo.getParticipants()-1);
      campaignRepo.save(campaignvo);
      
   }

   @Override
   public int selectCampaignId() {
      return campaignRepo.selectCampaignId();
   }

   @Override
   public void inserCampaignImg(CampaignImgvo campaignImgvo) {
      campaignImgRepo.save(campaignImgvo);
   }

   @Override
   public List<CampaignImgvo> selectCampaignImgList(int campaignId) {
      return campaignImgRepo.findByCampaignId(campaignId);
   }
   
   public List<Object[]> getCampaignSearchKeyword(String searchKeyword){
	   return campaignRepo.getCampaignSearchKeyword(searchKeyword);
   }

	@Override
	public List<Object[]> getListByActiveWithImg(Campaignvo campaignvo) {
		 
		return (List<Object[]>) campaignRepo.findAllByActiveWithImg();
	}
	
	@Override
	public List<Object[]> getListByEndDateWithImg(Campaignvo campaignvo) {
		 
		return (List<Object[]>) campaignRepo.findAllByOrderByEndDateAscWithImg();
	}
	
	@Override
	public List<Object[]> getListByPopularWithImg(Campaignvo campaignvo) {
		 
		return (List<Object[]>) campaignRepo.findAllByPopularByActiveWithImg();
	}

}