package com.example.demo.service.user;

import java.util.List;
import java.util.Optional;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserMapper;
import com.example.demo.domain.mypage.Uservo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private SqlSessionTemplate userSqlSessin;
	@Autowired
	private UserMapper userMapper;
	public List<Uservo> getSearchKeywordSearchPage(String searchKeyword){
		return userRepo.getSearchKeywordSearchPage(searchKeyword);
	}
	public List<Uservo> getAllFollowers(String followingYou){
		return userRepo.getAllFollowers(followingYou);
	}
	
	public List<Uservo> getAllFollowingUsers(String followingMe){
		return userRepo.getAllFollowingUsers(followingMe);
	}
	
	@Override
	public Uservo getUser(Uservo uservo) {
		
		/*
		 * JPAQueryFactory query = new JPAQueryFactory(em);
		 * 
		 * QUservo qUservo = QUservo.uservo;
		 * 
		 * String findByNickName = userRepo.findById(uservo.getNickname()).get();
		 * qUservo = query.selectFrom(qUservo)
		 * .where(qUservo.nickname.eq(uservo)).fetchOne();
		 */

		Optional<Uservo> findNickName = userRepo.findByNickName(uservo.getNickname());
		if (findNickName.isPresent()) 
			return findNickName.get();
		else return null;
			
	}

	// 유저를 리스트로 담아서 뿌려주는 임플 메소드
	@Override
	public List<Uservo> getUservoList(Uservo Uservo) {
		return (List<Uservo>) userRepo.findAll();
	}

	// 유저 정보를 높은 펄로우 수 순서대로 리스트 형식에 6명 저장해주는 메소드
	@Override
	public List<Object[]> getUservoListOrderByFollowingCountDes(){
		return userRepo.getAllOrderbyUserscoreDESC();
	}
	
	//유저 닉네임으로 검색하기
	@Override
	public List<Object[]> getSearchKeyword(String searchKeyword){
		return userRepo.getSearchKeyword(searchKeyword);
	}
	@Override

	public List<Uservo> getUservoListOrderByFollowingCountDes(Uservo uservo){
		return userRepo.findAllByIdOrderbyFollowingCountDESC(uservo);


	}
	// test용 메서드
	@Override
	public Optional<Uservo> insertUserId(Uservo user) {
		
		return userRepo.findById(new Uservo().getUserId());
	}

	public int joinUser(Uservo Uservo) {
		// TODO Auto-generated method stub
		return userMapper.joinUser(Uservo);
	}

	@Override
	public int emailCheck(Uservo vo, String user_email) {
		// TODO Auto-generated method stub
		userMapper = userSqlSessin.getMapper(UserMapper.class);// 아직 왜 인지 모름

		return userMapper.emailCheck(vo.getUser_email());
	}

	@Override
	public int nickCheck(Uservo vo,String nickname) {
		// TODO Auto-generated method stub
		return  userMapper.nickCheck(vo.getNickname());
	}
	
	////태원 -- 로그인 연동 못해서 작업용 로그인
	public Optional<Uservo> findById(Integer id) {
		return userRepo.findById(id);
	}
	
	//좋아요 누를시 유저 라이크평점 +1
	public void likeFavorFactor(String nickname) {
		userRepo.likeFavorFactor(nickname);
	}
	//좋아요 누를시 유저 라이크평점 -1
	public void unlikeFavorFactor(String nickname) {
		userRepo.unlikeFavorFactor(nickname);
	}
	//팔로 누릴시 유저 팔로우 +1
	public void followingCountIncrease(String nickname) {
		userRepo.followingCountIncrease(nickname);
	}
	//팔로 누를시 유저 팔로우 -1
	public void followingCountDecrease(String nickname) {
		userRepo.followingCountDecrease(nickname);
	}

	public List<Uservo> findByNickname(String nickname){
		return userRepo.findByNickname(nickname);
	}
	
	public void scrapFactorIncrease(Integer reviewUserId) {
		userRepo.scrapFactorIncrease(reviewUserId);
	}
	
	public void scrapFactorDecrease(Integer reviewUserId) {
		userRepo.scrapFactorDecrease(reviewUserId);
	}

	@Override
	public Uservo findByUserEmail(String user_email) {
		
		return userRepo.findByUserEmail(user_email);
	}
	
	public Page<Uservo> getSearchKeywordPage(String searchKeyword, Pageable pageable){
		return userRepo.getSearchKeywordPage(searchKeyword, pageable);
	}
}
