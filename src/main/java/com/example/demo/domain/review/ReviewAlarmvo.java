package com.example.demo.domain.review;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
public class ReviewAlarmvo {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reviewAlarmId;
	private String alarmContext;
	private Date alarmSendTime;
	private Date alarmReceiveTime;
	private int advertisementId;
	private int userId;
	private int reviewId;
}
