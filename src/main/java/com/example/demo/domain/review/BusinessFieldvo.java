package com.example.demo.domain.review;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity

public class BusinessFieldvo {

	@Id @GeneratedValue
	private int businessFieldId;
	private String businessFieldCode;
	private String businessFieldWes;
	private String businessFieldJpn;
	private String businessFieldChn;
	private String businessFieldInd;
	private String businessFieldSea;
	private String businessFieldSnk;
	private String businessFieldPaz;
	private String businessFieldChk;
	private String businessFieldFst;
}
