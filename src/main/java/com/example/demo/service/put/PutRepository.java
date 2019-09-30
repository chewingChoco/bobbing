package com.example.demo.service.put;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.mypage.Putvo;

public interface PutRepository extends JpaRepository<Putvo, Integer>{
	
	@Query(nativeQuery=true, value = ""
			+ "SELECT putvo.user_name, putvo.advertisement_name FROM putvo "
			+ "WHERE putvo.user_name = ?1 AND putvo.advertisement_name = ?2")
	public List<Object> checkPut(String followerMe, String advertisementName);
	
	@Query(nativeQuery=true, value=""
			+ "INSERT INTO putvo(user_name, advertisement_name) "
			+ "VALUES (?1, ?2)")
	public void put(String followerMe, String advertisementName);
	
	@Query(nativeQuery=true, value=""
			+ "DELETE FROM putvo "
			+ "WHERE putvo.user_name = ?1 AND putvo.advertisement_name = ?2")
	public void unput(String followerMe, String advertisementName);
	
	
}
