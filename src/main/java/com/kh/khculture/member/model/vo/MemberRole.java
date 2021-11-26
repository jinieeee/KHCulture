package com.kh.khculture.member.model.vo;

import lombok.Data;

@Data
public class MemberRole {
	private int mno;
	private int authorityCode;
	private String authorityKRName;
	private Authority authority;
	
	public MemberRole() {}
}
