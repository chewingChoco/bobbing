package com.example.demo.utils;

import org.springframework.data.domain.Page;

import com.example.demo.domain.mypage.Advertisementvo;
import com.example.demo.domain.mypage.Uservo;
import com.example.demo.domain.review.ReviewImagevo;
import com.example.demo.domain.review.ReviewRegistrationvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchPaging {
	
	ReviewRegistrationvo reviewRegistrationvo;
	ReviewImagevo reviewImagevo;
	Page<ReviewRegistrationvo> reviewRegistrationvoPage;
	String reviewImage;
	Object[] review;
	Uservo uservo;
	Advertisementvo advertisementvo;
//	List<Integer> totalPages = new ArrayList();
	int totalPages;
//	
//	private int countPerPage; // 페이지당 글목록 수							페이지 당 몇개 글
//	private int pagePerGroup;// 그룹당 페이지 수 								밑 그룹에 몇개 글이 담는가
//	private int currentPage;// 현재 페이지(최근 글이 1부터 시작)					지금 현재 페이지
//	private int totalRecordsCount;// 전체 글 수 								디비에 몇개의 글을 가지고 있는지
//	private int totalPageCount;// 전체 페잊 수 								총 몇개의 페이지
//	private int currentGroup;//현재 그룹(최근 그룹이 0 부터 시작)					현재 몇 번째 그룹인지 
//	private int startPageGroup;//현재 그룹의 첫 페이지							해당 그룹에 첫페이지
//	private int endPageGroup;//현재 그룹의 미지막 페이지							해당 그룹에 마지막 페이지
//	private int startRecord;//전체 레코드 중 현재 페이지 첫 글의 위치(0부터 시작)			해당 페이지의 첫 번째 글
//	
//	public void PageNavigator(int countPerPage, int pagePerGroup, int currentPage, int totalRecordsCount) {
//		//전체 페이지 수 
//		this.totalPageCount = (totalRecordsCount + countPerPage - 1)/ countPerPage;
//		
//		if(currentPage < 1)
//			currentPage = 1;
//		
//		if(currentPage > totalPageCount)
//			currentPage = totalPageCount;
//		this.currentPage = currentPage;
//		
//		//현재 그룹
//		currentGroup = (currentPage -1) / pagePerGroup;
//		
//		//현재 그룹의 첫 페이지
//		startPageGroup = currentGroup * pagePerGroup +1;
//		
//		//현재 그룹의 첫 페이지가 1보다 작으면 1로 처리
//		startPageGroup = startPageGroup < 1 ? 1 : startPageGroup;
//		
//		//현재 그룹의 마지막 페이지
//		endPageGroup = startPageGroup + pagePerGroup - 1;
//	
//		//현재 그룹의 마지막 페잊가 전체 페이지 수보다 작으면 전체페이지 수를 마지막으로
//		endPageGroup = endPageGroup < totalPageCount ? endPageGroup : totalPageCount;
//		
//		//전체 레코드 중 현재 페이지 첫 글의 위치
//		startRecord = (currentPage - 1) * countPerPage;
//	}
}
