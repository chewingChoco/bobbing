package com.example.demo.service.review;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.review.Commentvo;
import com.example.demo.domain.review.ReviewRegistrationvo;
import com.example.demo.service.scrap.ScrapRepository;

@Service
public class CommentServiceImp implements CommentService {

	@Autowired
	CommentRepository commentRepo;

	@Autowired
	ReviewRepository reviewRepo;

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	ScrapRepository scrapRepo;
//	@Override
//	public Optional<Commentvo> selectCommentListAllById(int reviewId) {
//		
//		
//		/*
//		 * JPAQueryFactory query = new JPAQueryFactory(em);
//		 * 
//		 * QReviewRegistrationvo qReviewRegistrationvo =
//		 * QReviewRegistrationvo.reviewRegistrationvo; QCommentvo qCommentvo =
//		 * QCommentvo.commentvo;
//		 * 
//		 * return query.selectFrom(qCommentvo)
//		 * .where(qCommentvo.reviewRegistrationvo.reviewId.eq(qReviewRegistrationvo.
//		 * reviewId)) .orderBy(qCommentvo.commentId.desc()).distinct().fetch();
//		 */
//		
//		
//		
//		return commentRepo.findById(reviewId);
//		
//	}

	@Override
	public void insertComment(Commentvo commentvo) {
		commentRepo.save(commentvo);
	}

	@Override
	public List<Commentvo> selectCommentList(ReviewRegistrationvo reviewRegistrationvo) {
		return commentRepo.findByReviewRegistrationvo(reviewRegistrationvo);
	}

	@Override
	public void deleteComment(int commentId) {
		commentRepo.deleteComment(commentId);
		System.out.println(commentRepo.findById(commentId).toString());
	}

	@Override
	public List<Commentvo> getCommentList(ReviewRegistrationvo reviewRegistrationvo) {
		return commentRepo.findByReviewRegistrationvo(reviewRegistrationvo);
	}
	
	@Override
	public void saveComment(Commentvo commentvo) throws Exception {
		commentRepo.save(commentvo);
	}

	@Override
	public void updateComment(Commentvo commentvo) {
		commentRepo.save(commentvo);
	}

	/*
	 * @Override public List<Commentvo> selectCommentList(ReviewRegistrationvo
	 * reviewRegistrationvo) {
	 * 
	 * 
	 * return commentRepo.findById(reviewRegistrationvo.getReviewId()); }
	 * 
	 * 
	 * JPAQueryFactory query = new JPAQueryFactory(em);
	 * 
	 * QReviewRegistrationvo qReviewRegistrationvo =
	 * QReviewRegistrationvo.reviewRegistrationvo; QCommentvo qCommentvo =
	 * QCommentvo.commentvo;
	 * 
	 * return query.selectFrom(qCommentvo)
	 * .where(qCommentvo.reviewRegistrationvo.reviewId.eq(qReviewRegistrationvo.
	 * reviewId)) .orderBy(qCommentvo.commentId.desc()).distinct().fetch();
	 * 
	 * // return commentRepo.findById(reviewId);
	 * 
	 * }
	 */
	
	

}
