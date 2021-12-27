package com.kh.khculture.oauth.model.vo;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import edu.emory.mathcs.backport.java.util.Collections;
import lombok.RequiredArgsConstructor;

// OAuth2UserService 사용자의 정보를 기반으로 가입 및 정보 수정, 세션 저장 등의 기능을 지원
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{
	private final UserRepository userRepository;
	private final HttpSession httpSession;
/*	
	@Autowired
	public CustomOAuth2UserService(UserRepository userRepository, HttpSession httpSession) {
		this.userRepository = userRepository;
		this.httpSession = httpSession;
	}
*/
	
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
		User user = saveOrUpdate(attributes);
		httpSession.setAttribute("user", new SessionUser(user)); // SessionUser (직렬화된 dto 클래스 사용)
		
		return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
				attributes.getAttributes(),
				attributes.getNameAttributeKey());
	}

	private User saveOrUpdate(OAuthAttributes attributes) {
		User user = userRepository.findByEmail(attributes.getEmail())
				.map(entity -> entity.update(attributes.getName()))
				.orElse(attributes.toEntity());
		return userRepository.save(user);
	}
}
