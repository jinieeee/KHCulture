package com.kh.khculture.oauth.model.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 열거형 클래스
@Getter
@RequiredArgsConstructor // final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
public enum Role {
	MEMBER("ROLE_MEMBER", "일반회원");
	
	private final String key;
	private final String title;
}
