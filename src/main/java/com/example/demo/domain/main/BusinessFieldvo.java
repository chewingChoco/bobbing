package com.example.demo.domain.main;

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


	@Id 
	private String businessFieldId;
	private String businessFieldName;
	
}
