package com.example.demo.service.advertisement;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.mypage.Advertisementvo;

public interface AdvertisementRepository extends JpaRepository<Advertisementvo, Integer>{
	
	//기업 정보 가지고와서 리스트에 저장하기

	@Query(nativeQuery=true, value = "SELECT advertisement_id, advertisement_name, profile_img, puted_count, evaluation_avg FROM advertisementvo ORDER BY evaluation_avg  DESC limit 6")

	List<Object[]> findAllbyAdvertisementidOrderbyEvaluationAvg();
	
	//기업 정보 서치
	
	
	@Query(nativeQuery=true, value = ""
			+ "SELECT * "
			+ "FROM advertisementvo WHERE advertisementname LIKE %?1% ",
			countQuery="SELECT COUNT(*) FROM advertisementvo WHERE advertisement_name LIKE %?1%")
	Page<Advertisementvo> getSearchKeyword(String searchKeyword, Pageable pageable);
	
	@Query(nativeQuery=true, value = ""
			+ "SELECT * "
			+ "FROM advertisementvo WHERE advertisementname LIKE %?1% ")
	List<Advertisementvo> getSearchKeywordSearchPage(String searchKeyword);
	
	List<Advertisementvo> findByAdvertisementname(String advertisementName);
	
	@Query(nativeQuery=true, value=""
			+ "SELECT * FROM advertisementvo "
			+ "INNER JOIN putvo "
			+ "ON advertisementvo.advertisementname = putvo.advertisement_name "
			+ "WHERE putvo.user_name = ?1 ")
	List<Advertisementvo> getAllUserPut(String username);
	
	@Query(nativeQuery=true, value=""
			+ "UPDATE advertisementvo "
			+ "SET puted_count = puted_count + 1 "
			+ "WHERE advertisement_name = ?1 ")
	void increasePut(String followerYou);
	
	@Query(nativeQuery=true, value=""
			+ "UPDATE advertisementvo "
			+ "SET puted_count = puted_count - 1 "
			+ "WHERE advertisement_name = ?1 ")
	void decreasePut(String followerYou);
//	List<Object[]> getSearchKeyword(String searchKeyword);
	
	@Query(value="SELECT * FROM advertisementvo WHERE advertisement_email = ?1", nativeQuery=true)
	Advertisementvo findByAdvertisementEmail(String advertisement_email);
}
