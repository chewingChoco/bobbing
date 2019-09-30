package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.campaign.Campaignvo;
import com.example.demo.domain.campaign.Participantsvo;
import com.example.demo.domain.campaign.TemporaryParticipantsvo;
import com.example.demo.domain.mypage.Uservo;
import com.example.demo.service.campaign.CampaignService;
import com.example.demo.service.campaign.ParticipantsService;
import com.example.demo.utils.TemporaryParticipants;

@Controller
public class ParticipantsController {

   @Autowired
   private ParticipantsService participantsService;
   
   @Autowired
   private CampaignService campaignService;
   
   @RequestMapping("/participantsCheck")
   @ResponseBody
   public int participantsCheck(Uservo uservo, Campaignvo campaignvo) throws Exception{
	   try {
		   return participantsService.getParticipants(uservo.getUserId(), campaignvo.getCampaignId());
	   } 
	   catch(Exception e) {
		   return 0;
	   }
   }
   
   @RequestMapping("/participants")
   @ResponseBody
   public TemporaryParticipants insertParticipants(Uservo uservo, Campaignvo campaignvo, Participantsvo participantsvo) {
      
      // 응모자 객체 생성
      participantsvo.setUservo(uservo);
      participantsvo.setCampaignvo(campaignvo);
      participantsService.insertParticipants(participantsvo);
      
      // 캠페인 참여자 카운트 증가
      Campaignvo campaignInfo = campaignService.getCampaign(campaignvo);
      campaignService.participantCountUp(campaignInfo);
      
      //
      Campaignvo campaignInfo2 = campaignService.getCampaign(campaignvo);
      TemporaryParticipants tempo = new TemporaryParticipants();
      tempo.setParticipants(campaignInfo2.getParticipants());
      tempo.setRecruitment(campaignInfo2.getRecruitment());
      
      return tempo;
   }

   @RequestMapping("/deleteParticipants")
   @ResponseBody
   public TemporaryParticipants deleteParticipants(Uservo uservo, Campaignvo campaignvo) {
      participantsService.deleteParticipants(uservo.getUserId(), campaignvo.getCampaignId());
      
      Campaignvo campaignInfo = campaignService.getCampaign(campaignvo);
      campaignService.participantCountDown(campaignInfo);
      
      Campaignvo campaignInfo2 = campaignService.getCampaign(campaignvo);
      TemporaryParticipants tempo = new TemporaryParticipants();
      tempo.setParticipants(campaignInfo2.getParticipants());
      tempo.setRecruitment(campaignInfo2.getRecruitment());
      
      return tempo;
   }
   
   @RequestMapping("/applyCheck")
   @ResponseBody
   public String applyCheck(int participantsId) {
      return participantsService.applyCheck(participantsId);
   }
   
   @RequestMapping("/selectParticipant")
   @ResponseBody
   public void selectParticipant(int participantsId) {
      participantsService.selectParticipants(participantsId);
   }
   
   @RequestMapping("/cancelParticipant")
   @ResponseBody
   public void cancelParticipant(int participantsId) {
      participantsService.cancleParticipants(participantsId);
   }
   
   @RequestMapping("/selectParticipantList")
   @ResponseBody
   public List<TemporaryParticipantsvo> selectParticipantList(int campaignId) {
      
      List<Participantsvo> participantsList = participantsService.getParticipantsList(campaignId);
      List<TemporaryParticipantsvo> TemporaryParticipantsList = new ArrayList<TemporaryParticipantsvo>();
      
      for(Participantsvo participantsvo: participantsList) {
         TemporaryParticipantsvo temporaryParticipantsvo = new TemporaryParticipantsvo();
         temporaryParticipantsvo.setCampaignId(participantsvo.getCampaignvo().getCampaignId());
         temporaryParticipantsvo.setParticipantsId(participantsvo.getParticipantsId());
         temporaryParticipantsvo.setParticipation(participantsvo.getParticipation());
         temporaryParticipantsvo.setUserId(participantsvo.getUservo().getUserId());
         temporaryParticipantsvo.setNickname(participantsvo.getUservo().getNickname());
         temporaryParticipantsvo.setProfileImg(participantsvo.getUservo().getProfileImg());
         TemporaryParticipantsList.add(temporaryParticipantsvo);
      }
      return TemporaryParticipantsList;
   }   
}