package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.domain.mypage.Advertisementvo;

@Mapper
public interface AdMapper {
	public int joinAd(Advertisementvo vo);

	public int ademailCheck(String advertisement_email);

	public int adnickCheck(String advertisementname);
	
	public int adsearchPassword(String advertisement_email,String password);

	public int alter_adKey(String advertisement_email, String key);

	public int GetKey(String advertisement_email, String key);
	
	public Advertisementvo loginAd(@Param("advertisement_email")String advertisement_email, @Param("password")String password);
}
