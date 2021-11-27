package com.kh.khculture.admin.model.vo;

import lombok.Data;

@Data
public class Notice {
	
	private int n_no; //공지사항 글 번호
	private String n_title; //공지사항 제목
	private String n_content; // 공지사항 내용
	private String n_enroll_date; // 작성일
	private int n_count; // 조회수
	private String n_modify_date; // 수정일
	private String n_status; // 게시글 삭제
	private int m_no; // 작성자 번호(관리자만 가능)
	private String id;
	private String refYN;

	public Notice() {}

}
