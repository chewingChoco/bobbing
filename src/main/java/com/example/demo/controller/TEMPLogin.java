package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.domain.mypage.Uservo;
import com.example.demo.service.user.UserService;

@Controller
@SessionAttributes("uservos")
public class TEMPLogin {
	
	@Autowired
	UserService userService;
	
	@ModelAttribute("uservo")
	public Uservo setUservo() {
		return new Uservo();
	}
	@RequestMapping("/templogin")
	public String tempLogin(@ModelAttribute("uservo")Uservo uservo, Integer userId, Model model) {
		Optional<Uservo> findUser = userService.findById(userId);
		uservo.setNickname(findUser.get().getNickname());
		model.addAttribute("username", uservo.getNickname());
		model.addAttribute("uservos",findUser);
		return "forward:/?followerMe="+uservo.getNickname()+"";
	}
	
	@RequestMapping("taewonlogin")
	public String taewonlogin() {
		return "th/TEMP/login";
	}

}
