package com.example.demo;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.campaign.Campaignvo;
import com.example.demo.domain.mypage.Uservo;
import com.example.demo.domain.review.ReviewRegistrationvo;
import com.example.demo.service.campaign.CampaignRepository;
import com.example.demo.service.review.ReviewRepository;
import com.example.demo.service.review.ReviewService;
import com.example.demo.service.user.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BabbingApplicationTests {
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ReviewRepository reviewRepo;
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	CampaignRepository campaignRepo;
	
	/*
	 * @Test public void contextLoads() { }
	 */
	
	/*
	 * @Test public String getReviewList(Model model) {
	 * 
	 * List<Uservo> userInfo = new ArrayList<Uservo>(); List<ReviewRegistrationvo>
	 * reviewList = new ArrayList<ReviewRegistrationvo>(); reviewList =
	 * reviewService.selectReviewList(); userInfo =
	 * reviewService.selectUservoInfo();
	 * 
	 * model.addAttribute("userInfo", userInfo); model.addAttribute("reviewList",
	 * reviewList);
	 * 
	 * for (ReviewRegistrationvo reviewRegistrationvo : reviewList) {
	 * System.out.println(reviewList.toString()); }
	 * 
	 * return "/review/reviewList"; }
	 */
	
	/*
	 * @Test public void getReviewMaincolumn() { Uservo user = new Uservo();
	 * user.setUser_id(1); Uservo saveUservo = userRepo.save(user);
	 * 
	 * ReviewRegistrationvo review = new ReviewRegistrationvo();
	 * review.setTitle("뽀로로가 가장 좋아하는"); review.setUservo(saveUservo);
	 * review.setAdvantages("뽀로로 사인회"); review.setDisadvantages("크롱싸인회");
	 * 
	 * ReviewRegistrationvo saveReview = reviewRepo.save(review);
	 * 
	 * reviewRepo.findByUservo_id(saveUservo.getUser_id(),
	 * SelectReviewMaincolumn.class).forEach(c-> {
	 * System.out.println(c.getIdAndNickName()); });
	 * 
	 * }
	 */
	
	/*
	 * @Test public void projectionTest() { reviewRepo.save(new
	 * ReviewRegistrationvo()); List<SelectReviewMaincolumn> review =
	 * reviewRepo.findByReview_id(1, SelectReviewMaincolumn.class); for
	 * (SelectReviewMaincolumn selectReviewMaincolumn : review) {
	 * System.out.println(selectReviewMaincolumn.toString()); } }
	 */
	/*
	 * @Test public void selectReviewIdJoinUserId(Uservo uservo,
	 * ReviewRegistrationvo reviewRegistrationvo) { JPAQueryFactory query = new
	 * JPAQueryFactory(em);
	 * 
	 * QUservo quservo = QUservo.uservo; QReviewRegistrationvo qreviewRegistrationvo
	 * = QReviewRegistrationvo.reviewRegistrationvo;
	 * 
	 * List<Object> jointest =(List<Object>)
	 * query.select(qreviewRegistrationvo).from(qreviewRegistrationvo).join(quservo)
	 * .on(quservo.userId.eq(qreviewRegistrationvo.userId)).fetchOne();
	 * 
	 * System.out.println(jointest.toString());
	 * 
	 * }
	 */
	/*
	 * @Test public void aaa () { List<Uservo> uservo = null;
	 * List<ReviewRegistrationvo> reviewRegistrationvo = null;
	 * 
	 * reviewRegistrationvo = reviewRepo.findAll(); uservo =(List<Uservo>)
	 * userRepo.findAll(); em.persist(uservo); em.persist(reviewRegistrationvo);
	 * reviewRegistrationvo.setUserId(uservo.UserId());
	 * uservo.setUserId(uservo.getUserId());
	 * System.out.println(reviewRegistrationvo.toString());
	 * System.out.println(uservo.toString());
	 * 
	 * }
	 */	
}
