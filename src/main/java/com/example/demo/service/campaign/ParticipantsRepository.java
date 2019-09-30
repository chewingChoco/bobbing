package com.example.demo.service.campaign;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.campaign.Participantsvo;

public interface ParticipantsRepository extends JpaRepository<Participantsvo, Integer> {

	
	// 캠페인 번호에 따른 응모자 리스트 호출
	@Query(value= "select * from participantsvo where campaign_id = ?1", nativeQuery = true)
	List<Participantsvo> getParticipantList(int campaignId);
	
	// 응모자 단일 객체 호출
	@Query(value="select count(*) from participantsvo where user_id = ?1 and campaign_id = ?2", nativeQuery = true)
	int getParticipant(int userId, int campaignId);
	
	// 응모 취소
	@Query(value="delete from participantsvo where user_id = ?1 and campaign_id = ?2", nativeQuery = true)
	void deleteParticipants(int advertisement_id, int campaign_id);

	@Query(value="select participation from participantsvo where participants_id = ?", nativeQuery = true)
	String applyCheck(int participantsId);
	
	@Query(value="SELECT profile_img FROM advertisementvo WHERE advertisement_id = ?", nativeQuery=true)
	String getAdvertisementProfileImg(int advertisement_id); 

}

