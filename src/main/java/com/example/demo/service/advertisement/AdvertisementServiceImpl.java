package com.example.demo.service.advertisement;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AdMapper;
import com.example.demo.domain.mypage.Advertisementvo;

@Service
public class AdvertisementServiceImpl implements AdvertisementService  {

	@Autowired
	AdvertisementRepository advertiseRepo;
	@Autowired
	private SqlSessionTemplate userSqlSessin;
	@Autowired
	private AdMapper adMapper;
	
	public List<Advertisementvo> getAllUserPut(String username){
		return advertiseRepo.getAllUserPut(username);
	}
	@Override
	public List<Object[]> getAdvertisementvoOrderByWeightAvg(){
		return advertiseRepo.findAllbyAdvertisementidOrderbyEvaluationAvg();
		
	}
	
	public List<Advertisementvo> findAll(){
		return advertiseRepo.findAll();
	}
	
	@Override
	public Page<Advertisementvo> getSearchKeyword(String searchKeyword, Pageable pageable) {
		return advertiseRepo.getSearchKeyword(searchKeyword, pageable);
	}

	@Override
	public int joinAd(Advertisementvo vo) {
		// TODO Auto-generated method stub
		return adMapper.joinAd(vo);
	}

	@Override
	public int ademailCheck(Advertisementvo vo, String advertisement_email) {
		// TODO Auto-generated method stub
		return adMapper.ademailCheck(vo.getAdvertisement_email());
	}

	@Override
	public int adnickCheck(Advertisementvo vo, String advertisementname) {
		// TODO Auto-generated method stub
		return adMapper.adnickCheck(vo.getAdvertisementname());
	}

	public List<Advertisementvo> findByAdvertisementName(String advertisementName){
		return advertiseRepo.findByAdvertisementname(advertisementName);
	}
	
	public void increasePut(String followerYou) {
		advertiseRepo.increasePut(followerYou);
	}
	
	public void decreasePut(String followerYou) {
		advertiseRepo.decreasePut(followerYou);
	}

	@Override
	public Advertisementvo findAllByAdvertisementEmail(String advertisement_email) {
		
		return advertiseRepo.findByAdvertisementEmail(advertisement_email);
	}
	
	public List<Advertisementvo> getSearchKeywordSearchpage(String searchKeyword){
		return advertiseRepo.getSearchKeywordSearchPage(searchKeyword);
	}
	
}
