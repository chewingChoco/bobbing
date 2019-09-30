package com.example.demo.domain.mypage;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.example.demo.domain.campaign.Campaignvo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude= {"advertisementEvaluationvo"})
@Entity
public class Advertisementvo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int advertisementId;
	private String advertisement_email;
	@Column(name="advertisementname")
	private String advertisementname;
	private String password;
	private String introduce;
	private String advertisement_num;
	private String address;
	private String profileImg;
	private String nonMembersCom;
	private String nonMembersAddress;
	private String ad_key;// 인증번호
	
	@Column(nullable = false, columnDefinition="int default 0")
	private int putedCount;

	
	@Column( columnDefinition="float default 0")
	private float flavor;					// 맛
	@Column( columnDefinition="float default 0")
	private float service;					// 서비스
	@Column( columnDefinition="float default 0")
	private float convenience;				// 편의
	@Column( columnDefinition="float default 0")
	private float price;					// 가성비
	@Column( columnDefinition="float default 0")
	private float evaluation_avg;			// 업체 평점	
	
	@JsonIgnore
	@OneToMany(mappedBy = "advertisementvo", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Campaignvo> campaignList = new ArrayList<Campaignvo>();
	
	@JsonIgnore
	@OneToOne(mappedBy ="advertisementvo")
	private AdvertisementEvaluationvo advertisementEvaluationvo;

}
