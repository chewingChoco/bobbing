package com.example.demo.service.user;

import javax.servlet.http.HttpServletRequest;

public interface UserMailSendService {

	public void mailSendWithUserKey(String user_email, String nickname, HttpServletRequest request);

	public int alter_userKey_service(String user_email, String key);

	public void mailSendWithAdKey(String advertisement_email, String advertisementname, HttpServletRequest request);

	public int alter_adKey_service(String advertisement_email, String key);

}
