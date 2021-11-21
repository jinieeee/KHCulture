package com.kh.khculture.board.model.vo;

public class board {

	private int b_no;
	private String b_title;
	private String b_content;
	private int m_no;
	private int lr_no;
	private String b_enroll_date;
	private String b_modify_date;
	private String b_status;
	private int b_count; // 조회수
	private int b_star; //벌점
	
	private Like like; // 뭉탱이로 가져올지 ,,, 개별로 할지..
	
	public board() {}

	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}

	public String getB_title() {
		return b_title;
	}

	public void setB_title(String b_title) {
		this.b_title = b_title;
	}

	public String getB_content() {
		return b_content;
	}

	public void setB_content(String b_content) {
		this.b_content = b_content;
	}

	public int getM_no() {
		return m_no;
	}

	public void setM_no(int m_no) {
		this.m_no = m_no;
	}

	public int getLr_no() {
		return lr_no;
	}

	public void setLr_no(int lr_no) {
		this.lr_no = lr_no;
	}

	public String getB_enroll_date() {
		return b_enroll_date;
	}

	public void setB_enroll_date(String b_enroll_date) {
		this.b_enroll_date = b_enroll_date;
	}

	public String getB_modify_date() {
		return b_modify_date;
	}

	public void setB_modify_date(String b_modify_date) {
		this.b_modify_date = b_modify_date;
	}

	public String getB_status() {
		return b_status;
	}

	public void setB_status(String b_status) {
		this.b_status = b_status;
	}

	public int getB_count() {
		return b_count;
	}

	public void setB_count(int b_count) {
		this.b_count = b_count;
	}

	public int getB_star() {
		return b_star;
	}

	public void setB_star(int b_star) {
		this.b_star = b_star;
	}

	public Like getLike() {
		return like;
	}

	public void setLike(Like like) {
		this.like = like;
	}

	@Override
	public String toString() {
		return "board [b_no=" + b_no + ", b_title=" + b_title + ", b_content=" + b_content + ", m_no=" + m_no
				+ ", lr_no=" + lr_no + ", b_enroll_date=" + b_enroll_date + ", b_modify_date=" + b_modify_date
				+ ", b_status=" + b_status + ", b_count=" + b_count + ", b_star=" + b_star + ", like=" + like + "]";
	}
	
	

}
