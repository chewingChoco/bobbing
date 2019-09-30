package com.example.demo.domain.campaign;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.domain.mypage.Advertisementvo;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude="advertisementvo")
@Entity
public class Campaignvo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int campaignId;
	
	private String title;
	
	private String introduction;
	
	@Column(nullable = false, columnDefinition="int default 0")
	private int recruitment;
	
	@Column(columnDefinition="int default 0")
	private int participants;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	
	private String offerHistory;
	private String remarks;

	
	@Column(insertable=false, updatable=false, columnDefinition="datetime default now()")
	private Date writeDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm")
	private Date modifiedDate;

	@ManyToOne
	@JoinColumn(name = "advertisement_id", nullable = false)
	@JsonBackReference
	private Advertisementvo advertisementvo;

}
