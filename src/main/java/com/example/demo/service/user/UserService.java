package com.example.demo.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.domain.mypage.Uservo;

public interface UserService {
	List<Uservo> getSearchKeywordSearchPage(String searchKeyword);
	
	List<Uservo> findByNickname(String nickname);
	
	List<Uservo> getAllFollowers(String followingYou);
	List<Uservo> getAllFollowingUsers(String followingMe);

	Uservo getUser(Uservo user);

	// 유저를 리스트로 담아서 뿌려주는 임플 메소드
	List<Uservo> getUservoList(Uservo uservo);

	Optional<Uservo> insertUserId(Uservo user);
	// 유저 정보를 높은 펄로우 수 순서대로 리스트 형식에 6명 저장해주는 메소드

	List<Object[]> getUservoListOrderByFollowingCountDes();

	// 유저 닉네임으로 검색하기
	List<Object[]> getSearchKeyword(String searchKeyword);

	// 유저 정보를 높은 펄로우 수 순서대로 리스트 형식에 6명 저장해주는 메소드
	List<Uservo> getUservoListOrderByFollowingCountDes(Uservo Uservo);

	public int joinUser(Uservo Uservo); // 회원가입 입니다.

	public int emailCheck(Uservo vo, String user_email);// 이메일 중복 확인 입니다.
	// @Param("user_email") 생략해도 돕니다.

	public int nickCheck(Uservo vo, String nickname);// 닉네임 중복 확인 입니다.
	
	//로그인 연동 못해서 임시적 로그인
	 Optional<Uservo> findById(Integer id);
	 
	 void likeFavorFactor(String nickname);
	 void unlikeFavorFactor(String nickname);
	 void followingCountIncrease(String nickname);
	 void followingCountDecrease(String nickname);
	 void scrapFactorIncrease(Integer reviewUserId);
	 void scrapFactorDecrease(Integer reviewUserId);
	 
	 Uservo findByUserEmail(String user_email);
	 
	 Page<Uservo> getSearchKeywordPage(String searchKeyword, Pageable pageable);

}