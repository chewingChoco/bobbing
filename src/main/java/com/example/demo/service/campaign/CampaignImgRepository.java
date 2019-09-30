package com.example.demo.service.campaign;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.campaign.CampaignImgvo;

public interface CampaignImgRepository extends JpaRepository<CampaignImgvo, Integer>{


	List<CampaignImgvo> findByCampaignId(int campaignId);

}

