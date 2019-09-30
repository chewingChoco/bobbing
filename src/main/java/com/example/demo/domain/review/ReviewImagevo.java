package com.example.demo.domain.review;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.demo.domain.mypage.Uservo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"tagList", "reviewRegistrationvo"})
@Entity
public class ReviewImagevo {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int imgId;
	@Column(nullable = true)
	private String img;
	@Column(nullable = true)
	private String imgReview;
	

	@ManyToOne
	@JoinColumn(name = "reviewId", nullable = true)
	private ReviewRegistrationvo reviewRegistrationvo;
	
	@OneToMany(mappedBy = "reviewImagevo",  cascade = CascadeType.ALL)
	private List<Tagvo> tagList = new ArrayList<Tagvo>();
	
	public void setReviewRegistrationvo(ReviewRegistrationvo reviewRegistrationvo) {
		this.reviewRegistrationvo = reviewRegistrationvo;
		reviewRegistrationvo.getReviewImgList().add(this);

	}

	
}
