package com.kh.khculture.member.model.vo;

public class PwdHint {
	private int hintNo;		// 힌트코드
	private String hintQ;	// 힌트문제
	
	public PwdHint() {}
	
	public PwdHint(int hintNo, String hintQ) {
		super();
		this.hintNo = hintNo;
		this.hintQ = hintQ;
	}

	public int getHintNo() {
		return hintNo;
	}

	public void setHintNo(int hintNo) {
		this.hintNo = hintNo;
	}

	public String getHintQ() {
		return hintQ;
	}

	public void setHintQ(String hintQ) {
		this.hintQ = hintQ;
	}

	@Override
	public String toString() {
		return "PwdHint [hintNo=" + hintNo + ", hintQ=" + hintQ + "]";
	}
	
	
}
