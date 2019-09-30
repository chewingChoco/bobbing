package com.example.demo.domain.review;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.demo.domain.mypage.Uservo;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = { "reviewRegistrationvo", "uservo" })
@Entity
public class Commentvo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentId;
	private String contents;
	private String nickname;
	@Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date writeDate;
	@Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date modifiedDate;

	@ManyToOne
	@JoinColumn(name = "reviewId")
	@JsonManagedReference
	private ReviewRegistrationvo reviewRegistrationvo;

	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonManagedReference
	private Uservo uservo;

	public void setReviewRegistrationvo(ReviewRegistrationvo reviewRegistrationvo) {
		this.reviewRegistrationvo = reviewRegistrationvo;
		reviewRegistrationvo.getCommentList().add(this);
	}

	public void setUservo(Uservo uservo) {
		this.uservo = uservo;
		uservo.getCommentList().add(this);
	}

}
