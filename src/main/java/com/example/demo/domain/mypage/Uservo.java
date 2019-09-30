package com.example.demo.domain.mypage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.demo.domain.review.Commentvo;
import com.example.demo.domain.review.ReviewRegistrationvo;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = { "reviewRegistrationList", "commentList" })
@Entity
public class Uservo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String nickname;
	private String password;
	private String introduce;
	@Column(name = "user_key")
	private String user_key;// 인증번호
	@Column(name = "user_email")
	private String user_email;

	@Column(nullable = true)
	private String profileImg;
	@Column(nullable = true, columnDefinition = "integer default 0")
	private Integer putCount;
	@Column(nullable = true, columnDefinition = "integer default 0")
	private Integer followingCount;
	@Column(nullable = true, columnDefinition = "integer default 0")
	private Integer snsFactor;
	@Column(nullable = true, columnDefinition = "integer default 0")
	private Integer favorFactor;
	@Column(nullable = true, columnDefinition = "integer default 0")
	private Integer scrapFactor;
	@Column(nullable = true, columnDefinition = "integer default 0")
	private Integer postCount;
	@Column(nullable = true, columnDefinition = "integer default 0")
	private Integer commentCount;
	@Column(nullable = true, columnDefinition = "integer default 0")
	private Double userScore;
	@Column(nullable = true, columnDefinition = "integer default 0")
	private Integer totalCount;
	@Column(nullable = true, columnDefinition = "integer default 0")
	private String uRankImg1;
	private String uRankImg2;
	private String uRankImg3;
	private String uRankImg4;
	private String uRankImg5;


	// 세션 테스트용 임시컬럼
	private String role;

	@OneToMany(mappedBy = "uservo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonBackReference
	private List<ReviewRegistrationvo> reviewRegistrationList = new ArrayList<ReviewRegistrationvo>();

	@OneToMany(mappedBy = "uservo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonBackReference
	public List<Commentvo> commentList = new ArrayList<Commentvo>();

}
