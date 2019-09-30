package com.example.demo.domain.review;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "reviewImagevo")
@Entity
public class Tagvo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tag_id")
	private int tagId;
	private String tagName;
	private String tagContent;
	private int tagX;
	private int tagY;
	@Column(nullable = true, insertable = false, updatable = false) //
	private int imgId;

	@ManyToOne
	@JoinColumn(name = "imgId", nullable = false)
	private ReviewImagevo reviewImagevo;

	/*
	 * public void setReviewImagevo(ReviewImagevo reviewImagevo) {
	 * this.reviewImagevo = reviewImagevo; reviewImagevo.getTagList().add(this);
	 * 
	 * }
	 */

}
