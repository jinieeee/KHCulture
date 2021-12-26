package com.kh.khculture.oauth.model.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User{
	// OAuth 로그인 시 사용할 사용자 객체
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;
	
	@Builder
	public User(String name, String email, Role role) {
		this.name = name;
		this.email = email;
		this.role = role;
	}
	
	public User update(String name) {
		this.name = name;
		return this;
	}
	
	public String getRoleKey() {
		return this.role.getKey();
	}
}
