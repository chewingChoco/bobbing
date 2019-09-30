package com.example.demo.domain.review;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Commentvo2 {
	private int commentId;
	private String contents;
	private String nickName;
	private int reviewId;
	private int userId;
	private String writeDate;
	private String modifyDate;

}
