package com.example.demo.domain.review;

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
public class ReviewTypevo {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reviewTypeId;
	private String reviewType;

	
}
