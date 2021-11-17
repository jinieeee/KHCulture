package com.kh.khculture.notice.model.vo;

public class Member_RoleTEST {
	private int m_no;
	private int authority_code;
	
	public Member_RoleTEST() {}

	public int getM_no() {
		return m_no;
	}

	public void setM_no(int m_no) {
		this.m_no = m_no;
	}

	public int getAuthority_code() {
		return authority_code;
	}

	public void setAuthority_code(int authority_code) {
		this.authority_code = authority_code;
	}

	@Override
	public String toString() {
		return "Member_RoleTEST [m_no=" + m_no + ", authority_code=" + authority_code + "]";
	}

	
	

}
