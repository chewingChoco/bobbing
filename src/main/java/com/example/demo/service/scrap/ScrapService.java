package com.example.demo.service.scrap;

import java.util.List;

public interface ScrapService {

	List<Object> checkScrap(Integer reviewId, String you);

	void insertScrap(Integer reviewId, String you);

	void deleteScrap(Integer reviewId, String you);

}