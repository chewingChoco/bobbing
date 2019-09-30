package com.example.demo.service.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.review.Commentvo;
import com.example.demo.domain.review.ReviewRegistrationvo;

public interface CommentRepository extends JpaRepository<Commentvo, Integer> {

	List<Commentvo> findByReviewRegistrationvo(ReviewRegistrationvo reviewRegistrationvo);

	@Query(value = "delete from commentvo where comment_id = ?", nativeQuery = true)
	void deleteComment(int commentId);
	
	@Query(value = "SELECT * FROM commentvo WHERE review_id = ?", nativeQuery = true)
	List<Commentvo> findByComment(int reviewId);

}
