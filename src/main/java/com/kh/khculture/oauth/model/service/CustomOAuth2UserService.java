package com.kh.khculture.oauth.model.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.kh.khculture.oauth.model.vo.CustomOath2User;
import com.kh.khculture.oauth.model.vo.OAuthAttributes;
import com.kh.khculture.oauth.model.vo.SessionUser;
import com.kh.khculture.oauth.model.vo.UserRepository;
import com.kh.khculture.oauth.model.vo.tempUser;

import edu.emory.mathcs.backport.java.util.Collections;

// OAuth2UserService 사용자의 정보를 기반으로 가입 및 정보 수정, 세션 저장 등의 기능을 지원
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{
	private final UserRepository userRepository;
	private final HttpSession httpSession;

	@Autowired
	public CustomOAuth2UserService(UserRepository userRepository, HttpSession httpSession) {
		this.userRepository = userRepository;
		this.httpSession = httpSession;
	}
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);
		
		// OAuth2 서비스 id
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		// OAuth2 로그인 진행 시 키가 되는 필드 값(PK)
		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
		
		// OAuth2UserService
		OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
		tempUser user = saveOrUpdate(attributes);
		
		httpSession.setAttribute("user", new SessionUser(user)); // SessionUser (직렬화된 dto 클래스 사용)
		httpSession.setAttribute("accessToken", userRequest.getAccessToken().getTokenValue());
		
		CustomOath2User customOath2User = new CustomOath2User(
												Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
												attributes.getAttributes(),
												attributes.getNameAttributeKey());
		customOath2User.setNickname(attributes.getName());
		return customOath2User;
	}

	private tempUser saveOrUpdate(OAuthAttributes attributes) {
		tempUser user = userRepository.findByEmail(attributes.getEmail())
				.map(entity -> entity.update(attributes.getName()))
				.orElse(attributes.toEntity());
		return userRepository.save(user);
	}
}
