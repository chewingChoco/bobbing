package com.example.demo.service.review;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.domain.mypage.Uservo;
import com.example.demo.domain.review.Commentvo;
import com.example.demo.domain.review.QReviewRegistrationvo;
import com.example.demo.domain.review.ReviewRegistrationvo;
import com.example.demo.service.user.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewRepository reviewRepo;

	@Autowired
	UserRepository userRepo;

	@PersistenceContext
	EntityManager em;

	public List<ReviewRegistrationvo> getEverythingWOLimit(){
		return (List<ReviewRegistrationvo>) reviewRepo.getEverythingWOLimit();
	}
	@Override
	public List<Object[]> getKoreanTopSix() {
		return reviewRepo.getKoreanFoodTopSix();
	}

	public List<ReviewRegistrationvo> getUserSelfWroteReview(Integer username) {
		return (List<ReviewRegistrationvo>) reviewRepo.getUserSelfWroteReview(username);
	}

	public List<ReviewRegistrationvo> getScrappedReview(String username) {
		return (List<ReviewRegistrationvo>) reviewRepo.getScrappedReview(username);
	}
//	@Override
//	public List<Object[]> getNewestReview() {
//		return reviewRepo.getNewestReview();
//	}

	@Override
	public List<ReviewRegistrationvo> getEverythingTopSix() {
		return (List<ReviewRegistrationvo>) reviewRepo.getEverything();
	}

	public List<Object[]> getEverythingTopSixObject() {
		return reviewRepo.getEverythingObject();
	}

	@Override
	public List<ReviewRegistrationvo> getBusinessFieldOne() {
		return (List<ReviewRegistrationvo>) reviewRepo.getBusinessFieldKor();
	}

	@Override
	public List<Object[]> getBusinessFieldOneObject() {
		return reviewRepo.getBusinessFieldKorObject();
	}

	@Override
	public List<ReviewRegistrationvo> getBusinessFieldTwo() {
		return (List<ReviewRegistrationvo>) reviewRepo.getBusinessFieldWes();
	}

	@Override
	public List<Object[]> getBusinessFieldTwoObject() {
		return reviewRepo.getBusinessFieldWesObject();
	}

	@Override
	public List<ReviewRegistrationvo> getBusinessFieldThree() {
		return (List<ReviewRegistrationvo>) reviewRepo.getBusinessFieldJpn();
	}

	@Override
	public List<Object[]> getBusinessFieldThreeObject() {
		return reviewRepo.getBusinessFieldJpnObject();
	}

	@Override
	public List<ReviewRegistrationvo> getBusinessFieldFour() {
		return (List<ReviewRegistrationvo>) reviewRepo.getBusinessFieldChn();
	}

	@Override
	public List<Object[]> getBusinessFieldFourObject() {
		return reviewRepo.getBusinessFieldChnObject();
	}

	@Override
	public List<ReviewRegistrationvo> getBusinessFieldFive() {
		return (List<ReviewRegistrationvo>) reviewRepo.getBusinessFieldSnk();
	}

	@Override
	public List<Object[]> getBusinessFieldFiveObject() {
		return reviewRepo.getBusinessFieldSnkObject();
	}

	@Override
	public List<ReviewRegistrationvo> getBusinessFieldSix() {
		return (List<ReviewRegistrationvo>) reviewRepo.getBusinessFieldFst();
	}

	@Override
	public List<Object[]> getBusinessFieldSixObject() {
		return reviewRepo.getBusinessFieldFstObject();
	}

	@Override
	public List<ReviewRegistrationvo> getBusinessFieldSeven() {
		return (List<ReviewRegistrationvo>) reviewRepo.getBusinessFieldCaf();
	}

	@Override
	public List<Object[]> getBusinessFieldSevenObject() {
		return reviewRepo.getBusinessFieldCafObject();
	}

	@Override
	public List<Object[]> getSearchKeyword(String searchKeyword) {
		return reviewRepo.getSearchKeyword(searchKeyword);
	}
//	@Override
//	public Page<Object[]> getSearchKeyword(String searchKeyword, PageRequest pageRequest) {
//		return reviewRepo.getSearchKeyword(searchKeyword, pageRequest);
//	}

	@Override
	public void insertReview(ReviewRegistrationvo reviewRegistrationvo) {
		reviewRepo.save(reviewRegistrationvo);
		
	}

	@Override
	public List<ReviewRegistrationvo> selectReviewList() {

		JPAQueryFactory query = new JPAQueryFactory(em);

		QReviewRegistrationvo qReviewRegistrationvo = QReviewRegistrationvo.reviewRegistrationvo;

		return query.selectFrom(qReviewRegistrationvo).orderBy(qReviewRegistrationvo.reviewId.desc()).fetch();

	}
	
	@Override
	public List<Object> selectReviewJoinReviewAndComment(Uservo uservo, ReviewRegistrationvo reviewRegistrationvo,
			Commentvo commentvo) {
		/*
		 * JPAQueryFactory query = new JPAQueryFactory(em); // JPAQuery<Object> query =
		 * new JPAQuery<Object>(em);
		 * 
		 * QUservo qUservo = QUservo.uservo; QReviewRegistrationvo qReviewRegistrationvo
		 * = QReviewRegistrationvo.reviewRegistrationvo; QCommentvo qCommentvo =
		 * QCommentvo.commentvo;
		 * 
		 */

		/*
		 * return query.from(qReviewRegistrationvo)
		 * .innerJoin(qReviewRegistrationvo.commentList, qCommentvo)
		 * .on(qReviewRegistrationvo.uservo.userId.eq(qCommentvo.uservo.userId))
		 * .orderBy(qReviewRegistrationvo.reviewId.desc()).fetch();
		 */
		return null;

	}

	@Override
	public void updateReview(ReviewRegistrationvo reviewRegistrationvo) {
		ReviewRegistrationvo findReview = reviewRepo.findById(reviewRegistrationvo.getReviewId()).get();

		findReview.setTitle(reviewRegistrationvo.getTitle());
		findReview.setAdvantages(reviewRegistrationvo.getAdvantages());
		findReview.setDisadvantages(reviewRegistrationvo.getDisadvantages());
		reviewRepo.save(findReview);

	}

	@Override
	public void deleteReview(int reviewId) {
		reviewRepo.delete(reviewId);
	}

//	@Override
//	public Tuple selectReviewIdJoinUserId(Uservo Uservo, ReviewRegistrationvo reviewRegistrationvo) {
//		JPAQueryFactory query = new JPAQueryFactory(em);
//		
//		QUservo qUservo = QUservo.uservo;
//		QReviewRegistrationvo qreviewRegistrationvo = QReviewRegistrationvo.reviewRegistrationvo;
//		
//		return query.select(qreviewRegistrationvo,qUservo.nickname).from(qreviewRegistrationvo)
//				.innerJoin(qUservo).on(qUservo.userId.eq(qreviewRegistrationvo.uservo)).fetchOne();
//		
//	}

	public Page<ReviewRegistrationvo> getSearchKeywordPage(String searchKeyword, Pageable pageable) {
		return reviewRepo.getSearchKeywordPage(searchKeyword, pageable);
	}

	public Page<ReviewRegistrationvo> findAll(Pageable pageable) {

		return reviewRepo.findAll(pageable);
	}

	public List<ReviewRegistrationvo> findAll() {
		return reviewRepo.findAll();
	}

	public List<ReviewRegistrationvo> getNewestReviewList() {
		return reviewRepo.getNewestReviewList();
	}

	public List<ReviewRegistrationvo> getNewestReview() {
		return (List<ReviewRegistrationvo>) reviewRepo.getNewestReview();
	}

	@Override
	public int createReviewId() {

		return (int) (reviewRepo.reviewCount()+1);
	}

	@Override
	public ReviewRegistrationvo getReviewView(int reviewId) {
		return reviewRepo.getReviewView(reviewId);

	}
	
	public List<ReviewRegistrationvo> getSearchKeywordSearchPage(String searchKeyword){
		return reviewRepo.getSearchKeywordSearchPage(searchKeyword);
	}

	@Override
	public int checkReviewId(int reviewId) {
		
		return reviewRepo.selectCheckReviewId(reviewId);
	}
	@Override
	public void modifyReviewView(ReviewRegistrationvo reviewRegistrationvo) {
		reviewRepo.save(reviewRegistrationvo);
	}
}
