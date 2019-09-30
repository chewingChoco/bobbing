package com.example.demo.service.review.image;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.review.ReviewImagevo;

@Service
public class ReviewImageServiceImpl implements ReviewImageService {

	@Autowired
	ReviewImageRepository reviewImageRepo;

	@Autowired
	
	@PersistenceUnit
    EntityManagerFactory emf;
	
	@PersistenceContext
	EntityManager em;

	@Override
	public ReviewImagevo getImgById(ReviewImagevo reviewImagevo) {
		return reviewImageRepo.findById(reviewImagevo.getImgId()).get();
	}

	@Override
	public ReviewImagevo getReviewImagevo(ReviewImagevo reviewImagevo) {
		return reviewImageRepo.findById(reviewImagevo.getImgId()).get();
	}

	public ReviewImagevo saveReviewImagevo(ReviewImagevo reviewImagevo) {
		return reviewImageRepo.save(reviewImagevo);

	}

	@Override
	public void insertReviewImg(ReviewImagevo reviewImagevo) {
		reviewImageRepo.save(reviewImagevo);

	}


	@Override
	public void updateReviewImg(ReviewImagevo reviewImagevo) {
		reviewImageRepo.save(reviewImagevo);
	}

	@Override
	public List<ReviewImagevo> getReviewImgList(int reviewId) {
		return reviewImageRepo.selectReviewImgList(reviewId);
	}
	
	/*
	 * @Override public List<ReviewImagevo> getAjaxReviewImgList(int reviewId) {
	 * 
	 * return null; }
	 */

	@Override
	public void deleteModifyReviewImg(String reviewViewImgName) {
		reviewImageRepo.deleteReviewViewImg(reviewViewImgName);
	}

	@Override
	public ReviewImagevo getReviewImagevoByImgName(String arrayshowImgSrc) {
		
		
		return reviewImageRepo.selectReviewImagevoByImgName(arrayshowImgSrc);
	}

	@Override
	public void updateShowImgReview(ReviewImagevo reviewImagevo) {
		reviewImageRepo.save(reviewImagevo);
	}


	/*
	 * @Override public Optional<ReviewImagevo> getReviewImgCheakOne(int reviewId) {
	 * return reviewImageRepo.findById(reviewId); }
	 */
}