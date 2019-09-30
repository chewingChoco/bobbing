//package com.example.demo.controller;
//
//import javax.annotation.Resource;
//import javax.inject.Inject;
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.social.connect.Connection;
//import org.springframework.social.connect.ConnectionFactoryLocator;
//import org.springframework.social.connect.ConnectionRepository;
//import org.springframework.social.connect.web.ConnectController;
//import org.springframework.social.facebook.api.Facebook;
//import org.springframework.social.facebook.api.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.servlet.view.RedirectView;
//
//import com.example.demo.domain.mypage.Uservo;
//
//@Controller
//@RequestMapping("/connect")
//public class FacebookTestController<signInUserDetailsService, signInUserDetailsService> extends ConnectController {
//	private String TARGET_URL = new String();
//
//	@Resource(name = "signInUserDetailsService")
//	private SignInUserDetailsService signInUserDetailsService;
//
//	@Resource(name = "inMemoryConnectionRepository")
//	private ConnectionRepository connectionRepository;
//
//	@Inject
//	public FacebookTestController(ConnectionFactoryLocator connectionFactoryLocator,
//			ConnectionRepository connectionRepository) {
//		super(connectionFactoryLocator, connectionRepository);
//	}
//
//	@RequestMapping(value = "/{providerId}", method = RequestMethod.POST)
//	public RedirectView connect(@PathVariable String providerId, NativeWebRequest request) {
//		HttpServletRequest httpServletRequest = (HttpServletRequest) request.getNativeRequest();
//		TARGET_URL = httpServletRequest.getHeader("REFERER");
//		return super.connect(providerId, request);
//	}
//
//	@RequestMapping(value = "/{providerId}", method = RequestMethod.GET, params = "code")
//	public RedirectView oauth2Callback(@PathVariable String providerId, NativeWebRequest request) {
//		RedirectView redirectView = super.oauth2Callback(providerId, request); // 사용자 정보 가져오기
//		Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
//		Facebook facebook = connection.getApi();
//		String[] fields = { "id", "age_range", "email", "first_name", "gender", "last_name", "link", "locale", "name",
//				"third_party_id", "verified" };
//		User userProfile = facebook.fetchObject("me", User.class, fields); // 로그인 처리
//
//		com.example.demo.controller.signInUserDetailsService.onAuthenticationBinding(new Uservo(), userProfile);
//
//		redirectView.setUrl(TARGET_URL);
//		return redirectView;
//	}
//
//}
