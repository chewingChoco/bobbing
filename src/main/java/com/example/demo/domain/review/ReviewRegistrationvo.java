package com.example.demo.domain.review;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.demo.domain.mypage.Uservo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"commentList", "uservo", "reviewImgList"})
@Entity
public class ReviewRegistrationvo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reviewId;
	private String reviewPlace;
	private String title;
	private String advantages;
	private String disadvantages;

//	@ManyToOne
//	@JoinColumn(name="user_id", nullable=false)
//	private Uservo uservo;

//	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date writeDate;
	@Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date modifiedDate;
	@Column(nullable = true) // 임시
	private Integer reviewTypeId;
	@Column(nullable = true) // 임시
	private String businessFieldId;
	@Column(nullable = true) // 임시
	private String themeId;
	
	


	/*
	 * @OneToMany(mappedBy = "reviewId", fetch = FetchType.EAGER, cascade =
	 * CascadeType.ALL) private List<ReviewImagevo> reviewImagevoList = new
	 * ArrayList<ReviewImagevo>();
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	@JsonManagedReference
	private Uservo uservo;

	@OneToMany(mappedBy = "reviewRegistrationvo", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JsonBackReference
	private List<Commentvo> commentList = new ArrayList<Commentvo>();

	
	@JsonIgnore
	@OneToMany(mappedBy = "reviewRegistrationvo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<ReviewImagevo> reviewImgList = new ArrayList<ReviewImagevo>();
	
	
	public void setUservo(Uservo uservo) {
		this.uservo = uservo;
		uservo.getReviewRegistrationList().add(this);

	}

}
