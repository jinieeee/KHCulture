package com.kh.khculture.member.model.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Mail {
	private String address;
	private String title;
	private String message;
	
	public Mail() {}
}
