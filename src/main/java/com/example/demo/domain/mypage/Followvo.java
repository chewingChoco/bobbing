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
public class Followvo {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int followId;
	@Temporal(value= TemporalType.TIMESTAMP)
	private Date followingTime;
	private String followerMe;
	private String followingYou;
}
