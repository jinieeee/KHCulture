package com.kh.khculture.notice.model.vo;

public class Authority_CodeTEST {
	private int authority_code;
	private String authority_name;
	private String authority_desc;
	
	public Authority_CodeTEST() {}

	public int getAuthority_code() {
		return authority_code;
	}

	public void setAuthority_code(int authority_code) {
		this.authority_code = authority_code;
	}

	public String getAuthority_name() {
		return authority_name;
	}

	public void setAuthority_name(String authority_name) {
		this.authority_name = authority_name;
	}

	public String getAuthority_desc() {
		return authority_desc;
	}

	public void setAuthority_desc(String authority_desc) {
		this.authority_desc = authority_desc;
	}

	@Override
	public String toString() {
		return "Authority_CodeTEST [authority_code=" + authority_code + ", authority_name=" + authority_name
				+ ", authority_desc=" + authority_desc + "]";
	}
	
	
}
