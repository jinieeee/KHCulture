package com.kh.khculture.notice.model.vo;

public class Notice {
// Date -> Mon Nov 15 17:58:19 KST 2021
// String -> 2021-11-15 17:58:19
	
	private int n_no; //공지사항 글 번호
	private String n_title; //공지사항 제목
	private String n_content; // 공지사항 내용
	private String n_enroll_date; // 작성일
	private int n_count; // 조회수
	private String n_modify_date; // 수정일
	private String n_status; // 게시글 삭제
//	private int m_no; // 작성자 번호(관리자만 가능)
	
	private Member_RoleTEST member_roletest; //게시글을 쓴 사람의 권한
	
	
	public Notice() {}


	public int getN_no() {
		return n_no;
	}


	public void setN_no(int n_no) {
		this.n_no = n_no;
	}


	public String getN_title() {
		return n_title;
	}


	public void setN_title(String n_title) {
		this.n_title = n_title;
	}


	public String getN_content() {
		return n_content;
	}


	public void setN_content(String n_content) {
		this.n_content = n_content;
	}


	public String getN_enroll_date() {
		return n_enroll_date;
	}


	public void setN_enroll_date(String n_enroll_date) {
		this.n_enroll_date = n_enroll_date;
	}


	public int getN_count() {
		return n_count;
	}


	public void setN_count(int n_count) {
		this.n_count = n_count;
	}


	public String getN_modify_date() {
		return n_modify_date;
	}


	public void setN_modify_date(String n_modify_date) {
		this.n_modify_date = n_modify_date;
	}


	public String getN_status() {
		return n_status;
	}


	public void setN_status(String n_status) {
		this.n_status = n_status;
	}


	public Member_RoleTEST getMember_roletest() {
		return member_roletest;
	}


	public void setMember_roletest(Member_RoleTEST member_roletest) {
		this.member_roletest = member_roletest;
	}


	@Override
	public String toString() {
		return "Notice [n_no=" + n_no + ", n_title=" + n_title + ", n_content=" + n_content + ", n_enroll_date="
				+ n_enroll_date + ", n_count=" + n_count + ", n_modify_date=" + n_modify_date + ", n_status=" + n_status
				+ ", member_roletest=" + member_roletest + "]";
	}

	
	
	
}
