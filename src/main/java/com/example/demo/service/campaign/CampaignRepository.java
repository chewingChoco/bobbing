package com.example.demo.service.campaign;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.campaign.Campaignvo;

public interface CampaignRepository extends JpaRepository<Campaignvo, Integer>{

	// 최신순 정렬
	@Query(value= "select * from campaignvo where end_date > now() order by write_date DESC", nativeQuery=true)
	List<Campaignvo> findAllByActive();
	
	// 인기순 정렬
	@Query(value= "select * from campaignvo where end_date > now() order by participants DESC", nativeQuery=true)
	List<Campaignvo> findAllByPopularByActive();
	
	// 마감 임박순 정렬
	@Query(value="select * from campaignvo where end_date > now() order by end_date ASC", nativeQuery=true)
	List<Campaignvo> findAllByOrderByEndDateAsc();	
	
	// 캠페인 삭제 
	@Query(value="DELETE FROM campaignvo WHERE campaign_id = ?1 ", nativeQuery=true)
	void deleteByCampaignId(int campaignId);

	@Query(value="select max(campaign_id) from campaignvo", nativeQuery = true)
	int selectCampaignId();
	

	//캠페인 검색
	@Query(nativeQuery=true, value=""
			+ "SELECT a.profile_img, a.advertisementname, c.end_date, ci.campaign_img, c.title, c.campaign_id "
			+ "FROM campaignvo c INNER JOIN campaign_imgvo ci "
			+ "ON c.campaign_id = ci.campaign_id "
			+ "INNER JOIN advertisementvo a "
			+ "ON c.advertisement_id = a.advertisement_id "
			+ "WHERE c.title LIKE %?1% ")
	List<Object[]> getCampaignSearchKeyword(String searchKeyword);

	@Query(nativeQuery=true, value=""
			+ "select c.campaign_id,c.title, c.end_date,c.write_date,c.participants, ci.campaign_img, ad.profile_img, ad.advertisementname "
			+ "from campaignvo c inner join campaign_imgvo ci on c.campaign_id = ci.campaign_id inner join advertisementvo ad on c.advertisement_id = ad.advertisement_id "
			+ "where c.end_date > now() group by c.campaign_id order by c.write_date DESC")	
	List<Object[]> findAllByActiveWithImg();	
	
	
	@Query(nativeQuery=true, value=""
			+ "select c.campaign_id,c.title, c.end_date,c.write_date,c.participants, ci.campaign_img, ad.profile_img, ad.advertisementname "
			+ "from campaignvo c inner join campaign_imgvo ci on c.campaign_id = ci.campaign_id inner join advertisementvo ad on c.advertisement_id = ad.advertisement_id "
			+ "where c.end_date > now() group by c.campaign_id order by c.participants DESC")
	List<Object[]> findAllByPopularByActiveWithImg();
	
	@Query(nativeQuery=true, value=""
			+ "select c.campaign_id,c.title, c.end_date,c.write_date,c.participants, ci.campaign_img, ad.profile_img, ad.advertisementname "
			+ "from campaignvo c inner join campaign_imgvo ci on c.campaign_id = ci.campaign_id inner join advertisementvo ad on c.advertisement_id = ad.advertisement_id "
			+ "where c.end_date > now() group by c.campaign_id order by c.end_date ASC")
	List<Object[]> findAllByOrderByEndDateAscWithImg();

}
