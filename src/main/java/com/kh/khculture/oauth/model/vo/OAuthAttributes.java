package com.kh.khculture.oauth.model.vo;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;

//OAuth2UserService를 통해 가져온 네이버 OAuth2User의 attributes를 담을 클래스

@Getter
public class OAuthAttributes {
	private Map<String, Object> attributes; // OAuth2 반환하는 유저 정보 Map
	private String nameAttributeKey;
	private String name;
	private String email;
	
	@Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
    	// 이 of 메소드로 어떤 로그인인지 구분할 수 있으나, 기본적으로 kakao만 설정
        return ofKakao("id", attributes);
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .role(Role.MEMBER) // 기본 권한 MEMBER
                .build();
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
    	// kakao는 kakao_account에 유저정보가 있다. (email)
    	Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
    	// kakao_account안에 또 profile이라는 JSON객체가 있다. (nickname)
    	Map<String, Object> kakaoProfile = (Map<String,Object>) kakaoAccount.get("profile");
    	
    	return OAuthAttributes.builder()
    			.name((String) kakaoProfile.get("nickname"))
    			.email((String) kakaoAccount.get("email"))
    			.attributes(attributes)
    			.nameAttributeKey(userNameAttributeName)
    			.build();
    }
}
