package com.example.demo.domain.mypage;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude= {"advertisementvo"})
@Entity
public class AdvertisementEvaluationvo {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int evaluationId;
	private float flavor;
	private float price;
	private float service;
	private float convenience;
	private float envaluationAvg;
	private String evaluationComment;
	private String merit;
	private String demerit;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="advertisementId", referencedColumnName = "advertisementId")
	private Advertisementvo advertisementvo;
	
	
}
