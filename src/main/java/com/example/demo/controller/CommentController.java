package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.mypage.Uservo;
import com.example.demo.domain.review.Commentvo;
import com.example.demo.domain.review.ReviewRegistrationvo;
import com.example.demo.service.review.CommentRepository;
import com.example.demo.service.review.CommentService;
import com.example.demo.service.review.ReviewRepository;
import com.example.demo.service.user.UserRepository;

@Controller
public class CommentController {

	@Autowired
	CommentService commentService;
	@Autowired
	ReviewRepository reviewRepo;
	@Autowired
	CommentRepository commentRepo;
	@Autowired
	UserRepository userRepo;

	@RequestMapping("deleteComment")
	@ResponseBody
	public void deleteComment(int commentId) {
		commentService.deleteComment(commentId);
	}
	
	@RequestMapping("updateComment")
	@ResponseBody
	public void updateComment(int commentId, int reviewId, int userId, String contents, String nickname) {
		System.out.println(commentId);
		System.out.println(contents);
		ReviewRegistrationvo reviewRegistrationvo = new ReviewRegistrationvo();
		Uservo uservo = new Uservo();
		reviewRegistrationvo.setReviewId(reviewId);
		uservo.setUserId(userId);
		
		Commentvo commentvo = new Commentvo();
		commentvo.setReviewRegistrationvo(reviewRegistrationvo);
		commentvo.setUservo(uservo);
		commentvo.setCommentId(commentId);
		commentvo.setContents(contents);
		commentvo.setNickname(nickname);
		commentService.updateComment(commentvo);
	}

	@RequestMapping("commentList")
	@ResponseBody
	public List<Commentvo> commentList(int reviewId) throws Exception {
		System.out.println(reviewId);
		
		ReviewRegistrationvo reviewRegistrationvo = new ReviewRegistrationvo();
		reviewRegistrationvo.setReviewId(reviewId);
		
		List<Commentvo> commentList = commentService.getCommentList(reviewRegistrationvo);
		return commentList;
	}

	@RequestMapping("/addComment2")
	@ResponseBody
	public Commentvo addComment(String contents, String nickname, int reviewId, int userId) throws Exception {
		
//		String httpComment = request.getInitParameter("contents");
		System.out.println(contents);
		System.out.println(reviewId);
		System.out.println(userId);
		System.out.println(nickname);

		Uservo uservo = new Uservo();
		uservo.setUserId(userId);
		
		ReviewRegistrationvo reviewRegistrationvo = new ReviewRegistrationvo();
		reviewRegistrationvo.setReviewId(reviewId);
		
		Commentvo commentvo = new Commentvo();
		commentvo.setReviewRegistrationvo(reviewRegistrationvo);
		commentvo.setUservo(uservo);
		commentvo.setContents(contents);
		commentvo.setNickname(nickname);
		commentService.saveComment(commentvo);
		System.out.println("댓글 추가"+commentvo.toString());
		
		return commentvo;

	}
}