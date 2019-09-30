package com.example.demo.service.rank;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.mypage.Followvo;

public interface RankRepository extends JpaRepository<Followvo, Integer>{
	
	@Query(nativeQuery=true, value=""
			+ "SELECT follower_me, following_you FROM followvo "
			+ "WHERE follower_me = ?1 AND following_you = ?2")
	List<Object> checkFollowing(String followerMe, String followingYou);

	@Query(nativeQuery=true, value=""
			+ "INSERT INTO followvo(follower_me, following_you) "
			+ "VALUES (?1, ?2)")
	void follow(String followerMe, String followingYou);
	
	@Query(nativeQuery=true, value=""
			+ "DELETE FROM followvo "
			+ "WHERE follower_me = ?1 AND following_you = ?2")
	void unfollow(String followerMe, String followingYou);
}
