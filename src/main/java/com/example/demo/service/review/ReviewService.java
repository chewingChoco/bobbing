package com.example.demo.service.review;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.domain.mypage.Uservo;
import com.example.demo.domain.review.AjaxReviewImagevo;
import com.example.demo.domain.review.Commentvo;
import com.example.demo.domain.review.ReviewRegistrationvo;

public interface ReviewService {

	List<ReviewRegistrationvo> getEverythingWOLimit();
//	List<Uservo> selectUservoInfo();
	List<ReviewRegistrationvo> getUserSelfWroteReview(Integer username);
	List<ReviewRegistrationvo> getScrappedReview(String username);
	List<ReviewRegistrationvo> findAll();
	List<Object[]> getKoreanTopSix();

	List<ReviewRegistrationvo> getNewestReview();

	List<ReviewRegistrationvo> getEverythingTopSix();
	List<Object[]> getEverythingTopSixObject();

	List<ReviewRegistrationvo> getBusinessFieldOne();
	List<Object[]> getBusinessFieldOneObject();

	List<ReviewRegistrationvo> getBusinessFieldTwo();
	List<Object[]> getBusinessFieldTwoObject();

	List<ReviewRegistrationvo> getBusinessFieldThree();
	List<Object[]> getBusinessFieldThreeObject();

	List<ReviewRegistrationvo> getBusinessFieldFour();
	List<Object[]> getBusinessFieldFourObject();

	List<ReviewRegistrationvo> getBusinessFieldFive();
	List<Object[]> getBusinessFieldFiveObject();

	List<ReviewRegistrationvo> getBusinessFieldSix();
	List<Object[]> getBusinessFieldSixObject();

	List<ReviewRegistrationvo> getBusinessFieldSeven();
	List<Object[]> getBusinessFieldSevenObject();

	Page<ReviewRegistrationvo> getSearchKeywordPage(String searchKeyword, Pageable pageable);
	List<Object[]> getSearchKeyword(String searchKeyword);
//	Page<Object[]> getSearchKeyword(String searchKeyword, PageRequest pageRequest);



//	List<ReviewRegistrationvo> selectReviewList();
	
	
	
	List<ReviewRegistrationvo> selectReviewList();

//	Tuple selectReviewIdJoinUserId(Uservo Uservo, ReviewRegistrationvo reviewRegistrationvo);

	void insertReview(ReviewRegistrationvo reviewRegistrationvo);



	
	
	List<Object> selectReviewJoinReviewAndComment(Uservo uservo, ReviewRegistrationvo reviewRegistrationvo, Commentvo commentvo);
	
	void updateReview(ReviewRegistrationvo reviewRegistrationvo);
	
	void deleteReview(int reviewId);
	
	int createReviewId();
	
	public Page<ReviewRegistrationvo> findAll(Pageable pageable);
	public List<ReviewRegistrationvo> getNewestReviewList();
	

	ReviewRegistrationvo getReviewView(int reviewId);
	

//	List<AjaxReviewImagevo> selectAjaxReviewImgList(int reviewId);
	List<ReviewRegistrationvo> getSearchKeywordSearchPage(String searchKeyword);
	
	int checkReviewId(int reviewId);
	
	void modifyReviewView(ReviewRegistrationvo reviewRegistrationvo);
	
	

}