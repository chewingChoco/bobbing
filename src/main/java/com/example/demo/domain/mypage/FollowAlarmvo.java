package com.example.demo.domain.mypage;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class FollowAlarmvo {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int followAlarmId;
	private String alarmContext;
	@Temporal(value= TemporalType.TIMESTAMP)
	private Date alarmSendTime;
	@Temporal(value= TemporalType.TIMESTAMP)
	private Date alarmReceiveTime;
	private int advertisementId;
	private int userId;
	private int followId;
}
