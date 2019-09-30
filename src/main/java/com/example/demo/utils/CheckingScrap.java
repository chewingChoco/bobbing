package com.example.demo.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.mypage.Uservo;
import com.example.demo.domain.review.ReviewImagevo;
import com.example.demo.domain.review.ReviewRegistrationvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CheckingScrap {

	@Autowired
	ReviewImagevo reviewImage;
	
	public ReviewRegistrationvo reviewRegistrationvo;
	public List<Object> scrapvo;
	public List<Object> followvo;
	public Uservo uservo;
	public List<Object[]> img;


	
	
	
		
}
