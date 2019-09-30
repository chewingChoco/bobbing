package com.example.demo.domain.common;

import java.sql.Timestamp;
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
public class LikeAlarmvo {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int likeAlarmId;
	private String alarmContext;
	@Temporal(value= TemporalType.TIMESTAMP)
	private Date alarmSendTime;
	@Temporal(value= TemporalType.TIMESTAMP)
	private Date alarmReceiveTime;
	private int advertisementId;
	private int userId;
	private int likeId;
}
