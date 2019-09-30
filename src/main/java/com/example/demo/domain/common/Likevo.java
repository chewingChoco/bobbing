package com.example.demo.domain.common;

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

public class Likevo {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int likeId;
	@Temporal(value= TemporalType.TIMESTAMP)
	private Date likeTime;
	private int reviewId;
	private int userId;
	
}
