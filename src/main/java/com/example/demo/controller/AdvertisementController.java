package com.example.demo.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.mypage.Advertisementvo;
import com.example.demo.service.advertisement.AdvertisementService;
import com.example.demo.service.user.UserMailSendService;
import com.example.demo.service.user.UserSha256;

@Controller
public class AdvertisementController {
	
	@Autowired
	AdvertisementService advertisementService;
	@Autowired
	private UserMailSendService mailsender;

	@RequestMapping("/ad")
	public String adMain() {
		return "th/main/homemain";
	}

	@RequestMapping("/adJoin")
	public String userJoin(Advertisementvo vo, Model model, HttpServletRequest request) {
		// 받아온 비밀번호를 암호화 합니다.
		String encryPassword = UserSha256.encrypt(vo.getPassword());
		// 암호화된 비밀번호를 set해줍니다.
		vo.setPassword(encryPassword);
		// 회원가입 메서드
		advertisementService.joinAd(vo);
		// 메일 보내기
		mailsender.mailSendWithAdKey(vo.getAdvertisement_email(),vo.getAdvertisementname(), request);
		return "joinMail";
	}

	@ResponseBody
	@RequestMapping("/ademailCheck")
	public int emailCheck(@RequestBody Advertisementvo vo) {
		System.out.println("기업 이멜체크");
		return advertisementService.ademailCheck(vo, vo.getAdvertisement_email());
	}

	// e-mail 인증 컨트롤러
	@RequestMapping(value = "/ad/key_alter", method = RequestMethod.GET)
	public String key_alterConfirm(@RequestParam("advertisement_email") String advertisement_email,
			@RequestParam("ad_key") String key) {

		mailsender.alter_adKey_service(advertisement_email, key);

		return "adRegSuccess";
	}
	@ResponseBody
	@RequestMapping("/adnickCheck")
	public int adnickCheck(@RequestBody Advertisementvo vo) {
		return advertisementService.adnickCheck(vo, vo.getAdvertisementname());
	}
	
	
}
