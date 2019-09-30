package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.mypage.Uservo;
import com.example.demo.domain.review.AjaxReviewImagevo;
import com.example.demo.domain.review.Commentvo;
import com.example.demo.domain.review.ReviewImagevo;
import com.example.demo.domain.review.ReviewRegistrationvo;
import com.example.demo.service.rank.RankService;
import com.example.demo.service.review.CommentService;
import com.example.demo.service.review.ReviewRepository;
import com.example.demo.service.review.ReviewService;
import com.example.demo.service.review.image.ReviewImageService;
import com.example.demo.service.scrap.ScrapService;
import com.example.demo.service.user.UserRepository;
import com.example.demo.service.user.UserService;
import com.example.demo.utils.CheckingScrap;

@Controller
public class ReviewController {

	@Autowired
	ReviewService reviewService;

	@Autowired
	UserService userService;

	@Autowired
	CommentService commentService;

	@Autowired
	ReviewImageService reviewImageService;

	@Autowired
	UserRepository userRepo;

	@Autowired
	ReviewRepository reviewRepo;

	@Autowired
	ScrapService scrapService;

	@Autowired
	RankService rankService;
	@PersistenceContext
	EntityManager em;

	@RequestMapping("/testhome")
	public String getReviewImagevo(ReviewImagevo reviewImagevo, Model model) {
		reviewImagevo.setImgId(1);
		model.addAttribute("review", reviewImageService.getReviewImagevo(reviewImagevo));
		return "th/main/homemain";
	}

//	@RequestMapping("/doReviewList")
//	public String getReviewList(Model model) {
//		List<ReviewRegistrationvo> reviewList = reviewService.selectReviewList();
//		model.addAttribute("reviewList", reviewList);
//		return "th/review/reviewList";
//	}

	@RequestMapping("/doReviewList")
	public String getReviewList(HttpSession httpSession, Model model) {
		String loggedInID = (String) httpSession.getAttribute("username");
		List<ReviewRegistrationvo> reviewList = reviewService.getEverythingWOLimit();
		List<CheckingScrap> reviewListCheckScrap = new ArrayList();
		for (int i = 0; i < reviewList.size(); i++) {
			CheckingScrap checkingScrap = new CheckingScrap();
			checkingScrap.setReviewRegistrationvo(reviewList.get(i));
			checkingScrap.setScrapvo(scrapService.checkScrap(reviewList.get(i).getReviewId(), loggedInID));
			checkingScrap
					.setFollowvo(rankService.checkFollowing(loggedInID, reviewList.get(i).getUservo().getNickname()));
			reviewListCheckScrap.add(checkingScrap);

		}
		model.addAttribute("reviewList", reviewListCheckScrap);
		return "th/review/reviewList";
	}

	@GetMapping("/insertReview")
	public String insertReview() {
		return "th/review/reviewWrite";
	}

	@PostMapping("/insertReview")
	public String insertReview(int userId, ReviewRegistrationvo reviewRegistrationvo, Optional<String> imgSrc, Optional<String> imgReview) {
		reviewRegistrationvo.setUservo(userRepo.findById(userId).get());
		reviewService.insertReview(reviewRegistrationvo);
		System.out.println("리뷰 이미지에 대한 내용 : " + imgReview);
		System.out.println("리뷰 이미지 주소 : " + imgSrc);

		if (imgSrc.get().isEmpty()) { // 이미지 주소값이 비어 있다면
			return "redirect:doReviewList";	// 리뷰작성을 리스트로 보냄
		} else {	// 이미지 주소값이 있다면
			String[] arrayImgSrc = imgSrc.get().split(",");	// 이미지값을 ,를 기준으로 나눠서 배열에 넣음
			if (imgReview.get().isEmpty()) {	// 만약 해당 이미지에 대한 리뷰값이 비어 있다면  
				for (int i = 0; i < arrayImgSrc.length; i++) {	// 나눠진 이미지 값의 배열을 리뷰 이미지값에 넣음
					ReviewImagevo reviewImagevo = new ReviewImagevo();
					String replaceSrc = arrayImgSrc[i].replace("s_", "").trim();
					reviewImagevo.setImg(replaceSrc);
					reviewImagevo.setReviewRegistrationvo(reviewRegistrationvo);
					reviewImageService.updateReviewImg(reviewImagevo);
					System.out.println("array [" + i + "]" + replaceSrc);
				}
			} else {	// 만약 해당 이미지에 대한 리뷰값이 들어있다면 
				String[] arrayImgReview = imgReview.get().split(",");	// 이미지에 대한 리뷰값을 ,를 기준으로 나눠서 배열에 넣음
				for (int i = 0, j = 0; i < arrayImgSrc.length || j < arrayImgReview.length; i++, j++) {	// 이미지 주소 배열의 수만큼
					ReviewImagevo reviewImagevo = new ReviewImagevo();
					String replaceSrc = arrayImgSrc[i].replace("s_", "").trim();	// 이미지 주소에 있는 섬네일을 나타내는 s_를 제거
					reviewImagevo.setImg(replaceSrc);
					try {
						if (arrayImgReview[j].trim().isEmpty()!=true) {	// 이미지에 대한 리뷰가 들어있다면
							String splitImgReview = arrayImgReview[j].trim();	// 이미지에 대한 리뷰를 ,를 기준으로 잘라서 배열에 넣는다
							reviewImagevo.setImgReview(splitImgReview);	// 잘라낸 이미지리뷰를 리뷰이미지 객체에 넣는다
							System.out.println("array [" + j + "]" + splitImgReview);
						}
					} catch (Exception e) {
						reviewImagevo.setImgReview(null);	// 만약 이미지에 대한 리뷰가 없을때는 이미지배열과 이미지리뷰의 배열이 달라서 예외가 발생하는데 이미지
					}										// 입력 되지 않은 이미지에 대한것은 널로 처리 된다
					reviewImagevo.setReviewRegistrationvo(reviewRegistrationvo);
					reviewImageService.updateReviewImg(reviewImagevo);	// 리뷰 이미지 주소와 이미지에 대한 리뷰를 저장
					System.out.println("array [" + i + "]" + replaceSrc);
				}
			}
		return "redirect:doReviewList";
		}
	}
	
	@PostMapping("/ReviewViewUpdate")
	public String ReviewViewUpdate(int userId, ReviewRegistrationvo reviewRegistrationvo, String showImgSrc, Optional<String> imgSrc, Optional<String> imgReview, Optional<String> showImgReview) {
		System.out.println("리뷰 업데이트 userId : " + userId);
		System.out.println("리뷰 업데이트 reviewRegistrationvo : " + reviewRegistrationvo.toString());
		System.out.println("리뷰 업데이트 imgReview : "+imgReview.toString());
		System.out.println("리뷰 업데이트 showImgReview : "+showImgReview.toString());
		System.out.println("리뷰 업데이트 showImgSrc : "+showImgSrc.toString());
		Uservo uservo = new Uservo();
		uservo.setUserId(userId);
		reviewRegistrationvo.setUservo(uservo);
		int reviewId = reviewRegistrationvo.getReviewId();
		System.out.println("리뷰 수정시 리뷰 아이디가 있는가? "+reviewId);
		reviewImageService.getReviewImgList(reviewId);
		Optional<Optional<String>> checkImgSrc = Optional.ofNullable(imgSrc);
		Optional<Optional<String>> checkShowImgReview = Optional.ofNullable(showImgReview);
		System.out.println("리뷰 이미지 업데이트 이미지 주소가 있는가? "+checkImgSrc.toString());
		if (checkImgSrc.get().isPresent()) {
			System.out.println("imgSrc값이 있음");

			String[] arrayImgSrc = imgSrc.get().toString().split(",");
			if (imgReview.get().isEmpty()) {	//해당 이미지에 대한 리뷰값이 비어있다면
				
				for (int i = 0; i < arrayImgSrc.length; i++) {
					ReviewImagevo reviewImagevo = new ReviewImagevo();
					String replaceSrc = arrayImgSrc[i].replace("s_", "").trim();
					reviewImagevo.setImg(replaceSrc);
					reviewImagevo.setReviewRegistrationvo(reviewRegistrationvo);
					reviewImageService.updateReviewImg(reviewImagevo);
					System.out.println("array [" + i + "]" + replaceSrc);
				}	// 이미지만 업데이트
			} else {	// 만약 해당 이미지에 대한 리뷰값이 들어있다면
				String[] arrayImgReview = imgReview.get().split(",");	// 이미지에 대한 리뷰값을 ,를 기준으로 나눠서 배열에 넣음
				for (int i = 0, j = 0; i < arrayImgSrc.length || j < arrayImgReview.length; i++, j++) {	// 이미지 주소 배열의 수만큼
					ReviewImagevo reviewImagevo = new ReviewImagevo();
					String replaceSrc = arrayImgSrc[i].replace("s_", "").trim();	// 이미지 주소에 있는 섬네일을 나타내는 s_를 제거
					reviewImagevo.setImg(replaceSrc);
					try {
						if (arrayImgReview[j].trim().isEmpty()!=true) {	// 이미지에 대한 리뷰가 들어있다면
							String splitImgReview = arrayImgReview[j].trim();	// 이미지에 대한 리뷰를 ,를 기준으로 잘라서 배열에 넣는다
							reviewImagevo.setImgReview(splitImgReview);	// 잘라낸 이미지리뷰를 리뷰이미지 객체에 넣는다
							System.out.println("array [" + j + "]" + splitImgReview);
						}
					} catch (Exception e) {
						reviewImagevo.setImgReview(null);	// 만약 이미지에 대한 리뷰가 없을때는 이미지배열과 이미지리뷰의 배열이 달라서 예외가 발생하는데 이미지
					}										// 입력 되지 않은 이미지에 대한것은 널로 처리 된다
					reviewImagevo.setReviewRegistrationvo(reviewRegistrationvo);
					reviewImageService.updateReviewImg(reviewImagevo);	// 리뷰 이미지 주소와 이미지에 대한 리뷰를 저장
					System.out.println("array [" + i + "]" + replaceSrc);
				}
			}
			
		}
		if (checkShowImgReview.get().isPresent()) {	// show이미지에 대한 리뷰가 있다면
			System.out.println("show리뷰 이미지가 있는가? "+checkShowImgReview);
			String[] arrayShowImgReview = showImgReview.get().split(",");
			if (showImgSrc.isEmpty()!=true) {	// show이미지주소가 있다면 
				System.out.println("show이미지주소가 있는가? "+showImgSrc);
				String[] arrayShowImgSrc = showImgSrc.split(",");
				for (int i = 0, j = 0; i < arrayShowImgSrc.length || j < arrayShowImgReview.length; i++) {
					ReviewImagevo reviewImagevo = new ReviewImagevo();
					System.out.println("for문체크");
					try {
						String splitShowImgSrc = arrayShowImgSrc[i];
						System.out.println("for문안에 스플릿 이미지주소"+splitShowImgSrc.toString());
						reviewImagevo = reviewImageService.getReviewImagevoByImgName(splitShowImgSrc);
						System.out.println("스플릿이미지vo로 해당 이미지주소가 찾아졌나? "+reviewImagevo.getImg().toString());
						reviewImagevo.setImgReview(arrayShowImgReview[j]);
						System.out.println("showImg주소체크 : "+reviewImagevo.toString());
						reviewImageService.updateShowImgReview(reviewImagevo);
					
						} catch (Exception e) {
							reviewImagevo.setImgReview(null);
						}
					}
			} else {	//show이미지 주소가 없다면(이미지가 없다면) 
				System.out.println("imgSrc값이 존재하지 않을 때");
				reviewRegistrationvo.setUservo(userRepo.findById(userId).get());
				reviewService.insertReview(reviewRegistrationvo);
				return "redirect:doReviewList";
			}
		} else {	// show이미지에 대한 리뷰가 없다면
			if (showImgReview.get().isEmpty()) {	// 만약 해당 이미지에 대한 리뷰값이 비어 있다면  
				String[] arrayShowImgSrc = showImgSrc.split(",");
//				String[] arrayShowImgReview = showImgReview.get().split(",");
				for (int i = 0; i < arrayShowImgSrc.length; i++) {	// 나눠진 이미지 값의 배열을 리뷰 이미지값에 넣음
					ReviewImagevo reviewImagevo = new ReviewImagevo();
					String replaceSrc = arrayShowImgSrc[i].replace("s_", "").trim();
					reviewImagevo.setImg(replaceSrc);
					reviewImagevo.setReviewRegistrationvo(reviewRegistrationvo);
					reviewImageService.updateReviewImg(reviewImagevo);
					System.out.println("array [" + i + "]" + replaceSrc);
				}
			}
		}
		return "redirect:doReviewList";
	}
	
	@RequestMapping(value = "/doReviewView", method = RequestMethod.GET)
	public String getReviewVIew(Model model, int reviewId, HttpSession httpSession) {
		System.err.println(reviewId);
//	      model.addAttribute("reviewView", reviewRepo.findById(reviewId).get());
		model.addAttribute("reviewView", reviewService.getReviewView(reviewId));
		model.addAttribute("reviewImgList", reviewImageService.getReviewImgList(reviewId));
		model.addAttribute("following", rankService.checkFollowing((String)httpSession.getAttribute("username"), reviewService.getReviewView(reviewId).getUservo().getNickname()));
		System.out.println(rankService.checkFollowing((String)httpSession.getAttribute("username"), reviewService.getReviewView(reviewId).getUservo().getNickname()));
		return "th/review/reviewView";
	}

	@RequestMapping("/deleteReviewView")
	public String deleteReview(int reviewId) {
		reviewService.deleteReview(reviewId);
		return "forward:doReviewList";
	}

	@RequestMapping(value = "/doReviewViewUpdate", method = RequestMethod.GET)
	public String doUpdateReview(Model model, int reviewId) {
		System.err.println(reviewId);
		ReviewImagevo reviewImagevo = new ReviewImagevo();
		ReviewRegistrationvo reviewRegistrationvo = new ReviewRegistrationvo();
		reviewRegistrationvo.setReviewId(reviewId);
		reviewImagevo.setReviewRegistrationvo(reviewRegistrationvo);
		System.out.println("리뷰 수정 이미지 리스트 : " + reviewImageService.getReviewImgList(reviewId).toString());
		model.addAttribute("reviewView", reviewService.getReviewView(reviewId));
		model.addAttribute("reviewImgList", reviewImageService.getReviewImgList(reviewId));

		return "th/review/reviewModify";
	}

	

	@RequestMapping("imageList")
	@ResponseBody
	public List<ReviewImagevo> imageList(int reviewId) throws Exception {
		System.err.println("showImageList ajax : " + reviewId);

		List<ReviewImagevo> imagetList = reviewImageService.getReviewImgList(reviewId);

		System.out.println("showImgList Ajax: "+imagetList.toString());
		return imagetList;
	}

}
