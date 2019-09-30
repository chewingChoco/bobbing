package com.example.demo.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.domain.campaign.CampaignImgvo;
import com.example.demo.domain.campaign.Campaignvo;
import com.example.demo.domain.mypage.Advertisementvo;
import com.example.demo.service.campaign.CampaignService;
import com.example.demo.service.campaign.ParticipantsService;

@Controller
public class CampaignController {


	@Autowired
	CampaignService campaignService;

	@Autowired
	ParticipantsService participantsService;
	
	// 기본 캠페인 리스트 호출
	@RequestMapping("/getCampaignList")
	public String getCampaignList(Model model, Campaignvo campaignvo) {
		
		List<Campaignvo> campaignList = campaignService.getListByActive(campaignvo);
		model.addAttribute("campaignList", campaignList);
		List<Object[]> campaignListWithImg = campaignService.getListByActiveWithImg(campaignvo);
		model.addAttribute("campaignListWithImg", campaignListWithImg);
		
		return "th/campaign/campaign";
	}
	
	// 라디오 버튼 값 선택에 따른 정렬
	@RequestMapping("/getCampaignListWithImageByRadio")
	public String getCampaignListByRadio(@RequestParam(value = "sort") String sort, Model model, Campaignvo campaignvo) {
		
		List<Object[]> campaignList;
		
		switch(sort) {
		case "recent" : 	
			campaignList = campaignService.getListByActiveWithImg(campaignvo);
			model.addAttribute("campaignListWithImg", campaignList);
			return "th/campaign/campaignSortList";
			
		case "end_date" : 	
			campaignList = campaignService.getListByEndDateWithImg(campaignvo);
			model.addAttribute("campaignListWithImg", campaignList);
			return "th/campaign/campaignSortList";
			
		case "popular" :
			campaignList = campaignService.getListByPopularWithImg(campaignvo);
			model.addAttribute("campaignListWithImg", campaignList); 
			return "th/campaign/campaignSortList";		
		default : return "th/campaign/campaignSortList";
		}

	}	

	// 캠페인 조회
	@GetMapping("/getCampaign")
	public String getCampaign(Model model, Campaignvo campaignvo) {

		Campaignvo campInfo = campaignService.getCampaign(campaignvo);
		model.addAttribute("campaign", campInfo);
		model.addAttribute("participantsList", participantsService.getParticipantsList(campInfo.getCampaignId()));
		model.addAttribute("profileImg", participantsService.getAdvertisementProfileImg(campInfo.getAdvertisementvo().getAdvertisementId()));
		
		List<CampaignImgvo> campaignImgList = campaignService.selectCampaignImgList(campInfo.getCampaignId());
				
		if(campaignImgList.size() == 1) {
			model.addAttribute("campaignSingleImg", campaignImgList);
		} else model.addAttribute("campaignImgList",campaignImgList);		
		
		return "th/campaign/campaignView";
	}
	
	// 캠페인 등록 페이지 이동
	@GetMapping("/insertCampaign")
	public String insertCampaignView() {
		return "th/campaign/campaignWrite";
	}

	// 캠페인 등록 실행
	@PostMapping("/insertCampaign")
	public String insertCampaign(Advertisementvo advertisementvo, Campaignvo campaignvo, MultipartHttpServletRequest request,
            @RequestParam("file") MultipartFile[] file) throws Exception {
		
		campaignvo.setAdvertisementvo(advertisementvo);
		campaignService.insertCampaign(campaignvo);
		
		int campaignId = campaignService.selectCampaignId();
		
		if(file.length!=0) {
			String uploadPath = request.getSession().getServletContext().getRealPath("/");
			String addPath = "campaignImages";
		
			File dirPath = new File(uploadPath+addPath);
			if (!dirPath.exists()) {
				dirPath.mkdir();
			}
			
			String fileOriginName = "";
		    for(int i=0; i<file.length; i++) {
		        fileOriginName = file[i].getOriginalFilename();
		        String uuid = UUID.randomUUID().toString();
		        File f = new File(dirPath+File.separator+uuid+"_"+fileOriginName);
		        file[i].transferTo(f);
		        
		        CampaignImgvo campaignImgvo = new CampaignImgvo();
		        campaignImgvo.setCampaignId(campaignId);
		        campaignImgvo.setCampaignImg(addPath+File.separator+uuid+"_"+fileOriginName);
		        
		        campaignService.inserCampaignImg(campaignImgvo);
		    }
		}		
		return "redirect:getCampaignList";
	}

	// 캠페인 수정 페이지 이동
	@GetMapping("/updateCampaign")
	public String updateCampaignView(Model model, Campaignvo campaignvo) {
		model.addAttribute("campaign", campaignService.getCampaign(campaignvo));
		List<CampaignImgvo> campaignImgList = campaignService.selectCampaignImgList(campaignvo.getCampaignId());
		model.addAttribute("campaignImgList",campaignImgList);
		
		return "th/campaign/campaignModify";
	}

	// 캠페인 수정 진행
	@PostMapping("/updateCampaign")
	public String updateCampaign(Campaignvo campaignvo) {
		campaignService.updateCampaign(campaignvo);
		return "forward:getCampaignList";
	}

	// 캠페인 삭제
	@GetMapping("/deleteCampaign")
	public String deleteCampaign(Campaignvo campaignvo) {
		campaignService.deleteCampaign(campaignvo);
		return "forward:getCampaignList";
	}
}

