package com.kh.khculture.member.model.vo;

public class MemberRole {
	private int mno;
	private int authorityCode;
	private Authority authority;
	
	public MemberRole() {}

	public MemberRole(int mno, int authorityCode, Authority authority) {
		super();
		this.mno = mno;
		this.authorityCode = authorityCode;
		this.authority = authority;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public int getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(int authorityCode) {
		this.authorityCode = authorityCode;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "MemberRole [mno=" + mno + ", authorityCode=" + authorityCode + ", authority=" + authority + "]";
	}
	
	
	
}
