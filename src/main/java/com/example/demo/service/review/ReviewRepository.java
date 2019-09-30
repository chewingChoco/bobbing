package com.example.demo.service.review;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.review.AjaxReviewImagevo;
import com.example.demo.domain.review.ReviewRegistrationvo;

public interface ReviewRepository extends JpaRepository<ReviewRegistrationvo, Integer>{
	
	@Query(nativeQuery=true, value="SELECT review_id, review_place, title, business_field_id FROM review_registrationvo where business_field_id ='1' ORDER BY write_date DESC LIMIT 6")
	List<Object[]> getKoreanFoodTopSix();
	
	@Query(nativeQuery=true, value=""
			+ "SELECT *, ri.img, user.profile_img, user.nickname "
			+ "FROM review_registrationvo AS r "
			+ "INNER JOIN review_imagevo AS ri "
			+ "ON r.review_id = ri.review_id "
			+ "INNER JOIN uservo AS user "
			+ "ON r.user_id = user.user_id "
			+ "JOIN scrapvo AS scrap "
			+ "ON user.nickname = scrap.username "
			+ "WHERE scrap.username = ?1 "
			+ "GROUP BY ri.review_id "
			+ "ORDER BY r.write_date DESC LIMIT 12 ")
	Iterable<ReviewRegistrationvo> getScrappedReview(String username);
	
	@Query(nativeQuery=true, value=""
			+ "SELECT *, ri.img, user.profile_img, user.nickname "
			+ "FROM review_registrationvo AS r "
			+ "INNER JOIN review_imagevo AS ri "
			+ "ON r.review_id = ri.review_id "
			+ "INNER JOIN uservo AS user "
			+ "ON r.user_id = user.user_id "
			+ "WHERE user.user_id = ?1 "
			+ "GROUP BY ri.review_id "
			+ "ORDER BY r.write_date DESC LIMIT 12 ")
	Iterable<ReviewRegistrationvo> getUserSelfWroteReview(Integer username);
	
//	@Query(nativeQuery=true, value=""
//			+ "SELECT r.review_id, r.review_place, r.title, r.business_field_id, ri.img, user.profile_img " + 
//			"FROM review_registrationvo AS r, review_imagevo AS ri, uservo AS user " + 
//			"where r.review_id = ri.review_id AND r.user_id = user.user_id" + 
//			"ORDER BY r.write_date DESC LIMIT 6")
	@Query(nativeQuery=true, value=""
			+ "SELECT * , ri.img, user.profile_img, user.nickname "
			+ "FROM review_registrationvo AS r "
			+ "INNER JOIN review_imagevo AS ri "
			+ "ON r.review_id = ri.review_id "
			+ "INNER JOIN uservo AS user "
			+ "ON r.user_id = user.user_id "
			+ "GROUP BY ri.review_id "
			+ "ORDER BY r.write_date DESC LIMIT 6")
	Iterable<ReviewRegistrationvo> getEverything();
	
//	@Query(nativeQuery=true, value=""
//			+ "SELECT * , ri.img, user.profile_img, user.nickname "
//			+ "FROM review_registrationvo AS r "
//			+ "INNER JOIN review_imagevo AS ri "
//			+ "ON r.review_id = ri.review_id "
//			+ "INNER JOIN uservo AS user "
//			+ "ON r.user_id = user.user_id "
//			+ "GROUP BY ri.review_id ")
//	Iterable<ReviewRegistrationvo> getEverythingWOLimit();
	@Query(nativeQuery=true, value=""
			+ "SELECT * , ri.img, user.profile_img, user.nickname "

			+ "FROM review_registrationvo AS r, review_imagevo AS ri, uservo AS user "
			+ "WHERE r.review_id = ri.review_id AND r.user_id = user.user_id "
			+ "GROUP BY r.review_id ORDER BY r.review_id DESC ")
	
	Iterable<ReviewRegistrationvo> getEverythingWOLimit();
	
	@Query(nativeQuery=true, value=""
			+ "SELECT r.review_id, r.review_place, r.title, r.business_field_id, ri.img, user.profile_img, user.nickname, user.user_id "
			+ "FROM review_registrationvo AS r INNER JOIN review_imagevo AS ri "
			+ "ON r.review_id = ri.review_id "
			+ "INNER JOIN uservo AS user "
			+ "ON r.user_id = user.user_id "
			+ "GROUP BY ri.review_id "
			+ "ORDER BY r.write_date DESC LIMIT 6")
	List<Object[]> getEverythingObject();
	
	@Query(nativeQuery=true, value=""
			+ "SELECT * , ri.img, user.profile_img, user.nickname "
			+ "FROM review_registrationvo AS r "
			+ "INNER JOIN uservo AS user "
			+ "ON r.user_id = user.user_id "
			+ "INNER JOIN review_imagevo AS ri "
			+ "ON r.review_id = ri.review_id "
			+ "GROUP BY ri.review_id "
			+ "ORDER BY r.write_date DESC LIMIT 3")
	Iterable<ReviewRegistrationvo> getNewestReview();
	
	@Query(nativeQuery=true, value="SELECT * "
			+ "FROM review_registrationvo AS r INNER JOIN review_imagevo AS ri "
			+ "ON r.review_id = ri.review_id "
			+ "INNER JOIN uservo AS user "
			+ "ON r.user_id = user.user_id "
			+ "ORDER BY r.write_date DESC LIMIT 3")
	List<ReviewRegistrationvo> getNewestReviewList();
	
	
	//한국음식
	@Query(nativeQuery=true, value=""
			+ "SELECT *, ri.img, user.profile_img, user.nickname "
			+ "FROM review_registrationvo AS r INNER JOIN review_imagevo AS ri "
			+ "ON r.review_id = ri.review_id "
			+ "INNER JOIN uservo AS user "
			+ "ON r.user_id = user.user_id "
			+ "AND r.business_field_id = 'kor' "
			+ "GROUP BY ri.review_id "
			+ "ORDER BY r.write_date DESC LIMIT 6")
	Iterable<ReviewRegistrationvo> getBusinessFieldKor();
	
	@Query(nativeQuery=true, value=""
			+ "SELECT r.review_id, r.review_place, r.title, r.business_field_id, ri.img, user.profile_img, user.nickname, user.user_id "
			+ "FROM review_registrationvo AS r INNER JOIN review_imagevo AS ri "
			+ "ON r.review_id = ri.review_id "
			+ "INNER JOIN uservo AS user "
			+ "ON r.user_id = user.user_id "
			+ "AND r.business_field_id = 'kor' "
			+ "GROUP BY ri.review_id "
			+ "ORDER BY r.write_date DESC LIMIT 6")
	List<Object[]> getBusinessFieldKorObject();
	
	//양식
	@Query(nativeQuery=true, value=""
			+ "SELECT *, ri.img, user.profile_img, user.nickname "
			+ "FROM review_registrationvo AS r INNER JOIN review_imagevo AS ri "
			+ "ON r.review_id = ri.review_id "
			+ "INNER JOIN uservo AS user "
			+ "ON r.user_id = user.user_id "
			+ "AND r.business_field_id = 'wes' "
			+ "GROUP BY ri.review_id "
			+ "ORDER BY r.write_date DESC LIMIT 6")
	Iterable<ReviewRegistrationvo> getBusinessFieldWes();
	
	@Query(nativeQuery=true, value=""
			+ "SELECT r.review_id, r.review_place, r.title, r.business_field_id, ri.img, user.profile_img, user.nickname  "
			+ "FROM review_registrationvo AS r INNER JOIN review_imagevo AS ri "
			+ "ON r.review_id = ri.review_id "
			+ "INNER JOIN uservo AS user "
			+ "ON r.user_id = user.user_id "
			+ "AND r.business_field_id = 'wes' "
			+ "GROUP BY ri.review_id "
			+ "ORDER BY r.write_date DESC LIMIT 6")
	List<Object[]> getBusinessFieldWesObject();
	
	//일식
	@Query(nativeQuery=true, value=""
			+ "SELECT *, ri.img, user.profile_img, user.nickname "
			+ "FROM review_registrationvo AS r INNER JOIN review_imagevo AS ri "
			+ "ON r.review_id = ri.review_id "
			+ "INNER JOIN uservo AS user "
			+ "ON r.user_id = user.user_id "
			+ "AND r.business_field_id = 'jpn' "
			+ "GROUP BY ri.review_id "
			+ "ORDER BY r.write_date DESC LIMIT 6")
	Iterable<ReviewRegistrationvo> getBusinessFieldJpn();
	
	
	@Query(nativeQuery=true, value=""
			+ "SELECT r.review_id, r.review_place, r.title, r.business_field_id, ri.img, user.profile_img, user.nickname  "
			+ "FROM review_registrationvo AS r INNER JOIN review_imagevo AS ri "
			+ "ON r.review_id = ri.review_id "
			+ "INNER JOIN uservo AS user "
			+ "ON r.user_id = user.user_id "
			+ "AND r.business_field_id = 'jpn' "
			+ "GROUP BY ri.review_id "
			+ "ORDER BY r.write_date DESC LIMIT 6")
	List<Object[]> getBusinessFieldJpnObject();
	
	//중식
	@Query(nativeQuery=true, value=""
			+ "SELECT *, ri.img, user.profile_img, user.nickname "
			+ "FROM review_registrationvo AS r INNER JOIN review_imagevo AS ri "
			+ "ON r.review_id = ri.review_id "
			+ "INNER JOIN uservo AS user "
			+ "ON r.user_id = user.user_id "
			+ "AND r.business_field_id = 'chn' "
			+ "GROUP BY ri.review_id "
			+ "ORDER BY r.write_date DESC LIMIT 6")
	Iterable<ReviewRegistrationvo> getBusinessFieldChn();
	
	
	@Query(nativeQuery=true, value=""
			+ "SELECT r.review_id, r.review_place, r.title, r.business_field_id, ri.img, user.profile_img, user.nickname  "
			+ "FROM review_registrationvo AS r INNER JOIN review_imagevo AS ri "
			+ "ON r.review_id = ri.review_id "
			+ "INNER JOIN uservo AS user "
			+ "ON r.user_id = user.user_id "
			+ "AND r.business_field_id = 'chn' "
			+ "GROUP BY ri.review_id "
			+ "ORDER BY r.write_date DESC LIMIT 6")
	List<Object[]> getBusinessFieldChnObject();
	
	//스낵?
	@Query(nativeQuery=true, value=""
			+ "SELECT *, ri.img, user.profile_img, user.nickname "
			+ "FROM review_registrationvo AS r INNER JOIN review_imagevo AS ri "
			+ "ON r.review_id = ri.review_id "
			+ "INNER JOIN uservo AS user "
			+ "ON r.user_id = user.user_id "
			+ "AND r.business_field_id = 'snk' "
			+ "GROUP BY ri.review_id "
			+ "ORDER BY r.write_date DESC LIMIT 6")
	Iterable<ReviewRegistrationvo> getBusinessFieldSnk();
	
	
	@Query(nativeQuery=true, value=""
			+ "SELECT r.review_id, r.review_place, r.title, r.business_field_id, ri.img, user.profile_img, user.nickname  "
			+ "FROM review_registrationvo AS r INNER JOIN review_imagevo AS ri "
			+ "ON r.review_id = ri.review_id "
			+ "INNER JOIN uservo AS user "
			+ "ON r.user_id = user.user_id "
			+ "AND r.business_field_id = 'snk' "
			+ "GROUP BY ri.review_id "
			+ "ORDER BY r.write_date DESC LIMIT 6")
	List<Object[]> getBusinessFieldSnkObject();
	
	//패스트푸드
	@Query(nativeQuery=true, value=""
			+ "SELECT *, ri.img, user.profile_img, user.nickname "
			+ "FROM review_registrationvo AS r INNER JOIN review_imagevo AS ri "
			+ "ON r.review_id = ri.review_id "
			+ "INNER JOIN uservo AS user "
			+ "ON r.user_id = user.user_id "
			+ "AND r.business_field_id = 'fst' "
			+ "GROUP BY ri.review_id "
			+ "ORDER BY r.write_date DESC LIMIT 6")
	Iterable<ReviewRegistrationvo> getBusinessFieldFst();
	
	
	@Query(nativeQuery=true, value=""
			+ "SELECT r.review_id, r.review_place, r.title, r.business_field_id, ri.img, user.profile_img, user.nickname  "
			+ "FROM review_registrationvo AS r INNER JOIN review_imagevo AS ri "
			+ "ON r.review_id = ri.review_id "
			+ "INNER JOIN uservo AS user "
			+ "ON r.user_id = user.user_id "
			+ "AND r.business_field_id = 'fst' "
			+ "GROUP BY ri.review_id "
			+ "ORDER BY r.write_date DESC LIMIT 6")
	List<Object[]> getBusinessFieldFstObject();
	
	//카페
	@Query(nativeQuery=true, value=""
			+ "SELECT *, ri.img, user.profile_img, user.nickname "
			+ "FROM review_registrationvo AS r INNER JOIN review_imagevo AS ri "
			+ "ON r.review_id = ri.review_id "
			+ "INNER JOIN uservo AS user "
			+ "ON r.user_id = user.user_id "
			+ "AND r.business_field_id = 'caf' "
			+ "GROUP BY ri.review_id "
			+ "ORDER BY r.write_date DESC LIMIT 6")
	Iterable<ReviewRegistrationvo> getBusinessFieldCaf();
	
	
	@Query(nativeQuery=true, value=""
			+ "SELECT r.review_id, r.review_place, r.title, r.business_field_id, ri.img, user.profile_img, user.nickname  "
			+ "FROM review_registrationvo AS r INNER JOIN review_imagevo AS ri "
			+ "ON r.review_id = ri.review_id "
			+ "INNER JOIN uservo AS user "
			+ "ON r.user_id = user.user_id "
			+ "AND r.business_field_id = 'caf' "
			+ "GROUP BY ri.review_id "
			+ "ORDER BY r.write_date DESC LIMIT 6")
	List<Object[]> getBusinessFieldCafObject();
	
	//리뷰 서치
	@Query(nativeQuery=true, value=""
			+ "SELECT r.review_id, r.review_place, r.title, r.business_field_id, ri.img, user.profile_img, user.nickname  "
			+ "FROM review_registrationvo AS r INNER JOIN review_imagevo AS ri "
			+ "ON r.review_id = ri.review_id "
			+ "INNER JOIN uservo AS user "
			+ "ON r.user_id = user.user_id "
			+ "AND r.title LIKE %:searchKeyword% ")
	List<Object[]> getSearchKeyword(String searchKeyword);
	//리뷰 서치
	@Query(nativeQuery=true, value=""
			+ "SELECT *  "
			+ "FROM review_registrationvo AS r INNER JOIN review_imagevo AS ri "
			+ "ON r.review_id = ri.review_id "
			+ "INNER JOIN uservo AS user "
			+ "ON r.user_id = user.user_id "
			+ "AND r.title LIKE %:searchKeyword% ")
	List<ReviewRegistrationvo> getSearchKeywordSearchPage(String searchKeyword);
	@Query(nativeQuery=true, value=""
			+ "SELECT r.review_id, r.review_place, r.title, r.business_field_id, ri.img, user.profile_img, user.nickname "
			+ "FROM review_registrationvo as r INNER JOIN review_imagevo as ri "
			+ "ON r.review_id = ri.review_id "
			+ "INNER JOIN uservo as user "
			+ "ON r.user_id = user.user_id "
			+ "WHERE r.title LIKE %?1% ",
			countQuery = "SELECT count(r.review_id) FROM review_registrationvo AS r "
					+ "INNER JOIN review_imagevo AS ri "
					+ "ON r.review_id = ri.review_id "
					+ "INNER JOIN uservo as user "
					+ "ON r.user_id = user.user_id "
					+ "WHERE title LIKE %?1% " )
//			+ "LIMIT 8 ")
	Page<ReviewRegistrationvo> getSearchKeywordPage(String searchKeyword, Pageable pageable);
	
//	@Query("SELECT r, ri.img, user.profileImg, user.nickname FROM ReviewRegistartionvo r  "
//			+ "INNER JOIN review_imagevo ri "
//			+ "ON r.reviewId = ri.reviewId "
//			+ "INNER JOIN uservo user "
//			+ "ON r.userId = user.userId "
//			+ "WHERE r.title LIKE %?1% ")
//	Page<ReviewRegistrationvo> getSearchKeywordPage(String searchKeyword, Pageable pageable);

//	Page<Object[]> getSearchKeyword(String searchKeyword, PageRequest pageRequest);

	@Query(value = "DELETE FROM review_registrationvo WHERE review_id = ?", nativeQuery = true)
	void delete(int reviewId);

	
	@Query(value = "SELECT * FROM review_registrationvo WHERE review_id = ?", nativeQuery = true)
	ReviewRegistrationvo getReviewView(int reviewId);
	
	@Query(value = "SELECT review_id FROM review_registrationvo ORDER BY review_id DESC  LIMIT 1", nativeQuery = true)
	int reviewCount();
	
	@Query(value = "SELECT review_id FROM review_registrationvo WHERE review_id = ?", nativeQuery = true)
	int selectCheckReviewId(int reviewId);
}
