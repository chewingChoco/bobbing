package com.example.demo.service.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.mypage.Uservo;

public interface LoginRepository extends JpaRepository<Uservo, Integer>{
	
	@Query("SELECT nickname FROM Uservo u WHERE u.user_email like %?1%")
	String selectNickName(String userEmail);
	@Query("SELECT userId FROM Uservo u WHERE u.nickname like %?1%")
	String selectUserId(String nickName);
}
