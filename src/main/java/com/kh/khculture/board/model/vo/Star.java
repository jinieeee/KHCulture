package com.kh.khculture.board.model.vo;

public class Like {
	
	private int m_no;
	private int b_no;
	private String enroll_date;
	
	public Like() {}

	public int getM_no() {
		return m_no;
	}

	public void setM_no(int m_no) {
		this.m_no = m_no;
	}

	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}

	public String getEnroll_date() {
		return enroll_date;
	}

	public void setEnroll_date(String enroll_date) {
		this.enroll_date = enroll_date;
	}

	@Override
	public String toString() {
		return "Like [m_no=" + m_no + ", b_no=" + b_no + ", enroll_date=" + enroll_date + "]";
	}
	
	
	

}
