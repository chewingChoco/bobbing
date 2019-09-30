package com.example.demo.service.user;

import javax.servlet.http.HttpServletRequest;

public interface PwSearchService {
	public void mailSendWithPassword(String user_email, String nickname, HttpServletRequest request);
	public void admailSendWithPassword(String advertisement_email, HttpServletRequest request);
	public int searchPassword(String user_email, String password);
	public int adsearchPassword(String advertisement_email, String password);
}
