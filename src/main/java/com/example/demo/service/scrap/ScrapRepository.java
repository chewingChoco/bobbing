package com.example.demo.service.scrap;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.mypage.Scrapvo;


public interface ScrapRepository extends JpaRepository<Scrapvo, Integer>{

	@Query(nativeQuery=true, value=""
			+ "SELECT scrapvo.review_id, scrapvo.username "
			+ "FROM scrapvo "
			+ "WHERE scrapvo.review_id = ?1 AND scrapvo.username = ?2 ")
	public List<Object> checkScrap(Integer reviewId, String you);
	
	@Query(nativeQuery=true, value=""
			+ "INSERT INTO scrapvo(review_id, username) "
			+ "VALUES (?1, ?2)")
	public void insertScrap(Integer reviewId, String you);
	
	@Query(nativeQuery=true, value=""
			+ "DELETE FROM scrapvo "
			+ "WHERE review_id = ?1 AND username = ?2")
	public void deleteScrap(Integer reviewId, String you);
}
