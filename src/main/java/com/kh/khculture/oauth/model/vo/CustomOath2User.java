package com.kh.khculture.oauth.model.vo;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomOath2User extends DefaultOAuth2User{
	
	private String nickname;
	
	public CustomOath2User(Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes,
			String nameAttributeKey) {
		super(authorities, attributes, nameAttributeKey);
	}

	
}
