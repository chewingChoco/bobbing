package com.example.demo.domain.mypage;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Scrapvo {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int scrapId;
	private Timestamp scrapTime;
	private int reviewId;
	private String username;
	
}
