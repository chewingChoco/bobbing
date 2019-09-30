package com.example.demo.domain.campaign;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TemporaryParticipantsvo {

	
	private int participantsId;
	private int campaignId;
	private int userId;
	private char participation;
	private String nickname;
	private String profileImg;
}

