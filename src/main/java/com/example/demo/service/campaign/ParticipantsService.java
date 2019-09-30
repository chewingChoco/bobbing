package com.example.demo.service.campaign;

import java.util.List;

import com.example.demo.domain.campaign.Participantsvo;

public interface ParticipantsService {

	List <Participantsvo> getParticipantsList(int campaignId);
	
	int getParticipants(int userId, int campaignId);
	
	// 응모하기
	void insertParticipants(Participantsvo participants);
	// 응모취소
	void deleteParticipants(int userId, int campaignId);	
	// 선정하기	
	void selectParticipants(int participantsId);
	// 선정취소
	void cancleParticipants(int participantsId);

	String applyCheck(int participantsId);
	
	String getAdvertisementProfileImg(int advertisement_id);

}
