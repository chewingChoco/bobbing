package com.example.demo.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.domain.mypage.Uservo;

@Mapper
public interface UserMapper {
	public int joinUser (Uservo userVO); // 회원가입 입니다.
	public int emailCheck(@Param("user_email") String user_email);// 이메일 중복 확인 입니다.
	//@Param("user_email") 생략해도 돕니다.
	public int nickCheck(String nickname);// 닉네임 중복 확인 입니다.
	
	
	public int GetKey(String user_email, String user_key); // 유저 인증키 생성 메서드
	public int alter_user_key(String user_email, String key); // 유저 인증키 Y로 바꿔주는 메서드
	public int searchPassword(String user_email,String password); // 회원 임시 비밀번호 변경 메서드
	public Uservo loginUser(@Param("user_email")String user_email, @Param("user_pw")String user_pw);// 유저 로그인 메서드
//우선만듬
	public Uservo userInfo(String user_email);

	


	
	
}
