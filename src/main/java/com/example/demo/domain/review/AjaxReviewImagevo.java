package com.example.demo.domain.review;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class AjaxReviewImagevo {

   @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
   private int ajaxReviewImgId;
   private String ajaxReviewImg;
//   @Column(name = "ajax_review_id")
   private int reviewId;
   
}
