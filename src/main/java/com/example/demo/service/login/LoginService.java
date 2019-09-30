package com.example.demo.service.login;

import javax.servlet.http.HttpSession;

import com.example.demo.domain.mypage.Advertisementvo;
import com.example.demo.domain.mypage.Uservo;

public interface LoginService {

	int userLogin_service(Uservo Uservo, String user_check, HttpSession session);

	int adLogin_service(Advertisementvo vo, String user_check, HttpSession session);

}