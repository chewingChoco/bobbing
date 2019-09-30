package com.example.demo.service.campaign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.campaign.Participantsvo;

@Service
public class ParticipantsServiceImpl implements ParticipantsService {

	@Autowired
	private ParticipantsRepository participantsRepo;
	
	@Override
	public List<Participantsvo> getParticipantsList(int campaignId) {
		return (List<Participantsvo>) participantsRepo.getParticipantList(campaignId);
	}

	@Override
	public int getParticipants(int userId, int campaignId) {
		return participantsRepo.getParticipant(userId, campaignId);
	}

	@Override
	public void insertParticipants(Participantsvo participants) {
		participants.setParticipation('N');
		participantsRepo.save(participants);
	}

	@Override
	public void deleteParticipants(int advertisementId, int campaignId) {
		participantsRepo.deleteParticipants(advertisementId, campaignId);	
	}

	@Override
	public void selectParticipants(int participantsId) {
		Participantsvo participantsvo = participantsRepo.findById(participantsId).get();
		participantsvo.setParticipation('Y');
		participantsRepo.save(participantsvo);
	}

	@Override
	public void cancleParticipants(int participantsId) {
		Participantsvo participantsvo = participantsRepo.findById(participantsId).get();
		participantsvo.setParticipation('N');
		participantsRepo.save(participantsvo);
	}

	@Override
	public String applyCheck(int participantsId) {
		return participantsRepo.applyCheck(participantsId);
	}

	@Override
	public String getAdvertisementProfileImg(int advertisement_id) {
		return participantsRepo.getAdvertisementProfileImg(advertisement_id);
	}

}

