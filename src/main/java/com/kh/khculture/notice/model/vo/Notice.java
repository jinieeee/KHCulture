package com.kh.khculture.notice.model.vo;

import java.util.Date;

import lombok.Data;

@Data
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
	private int m_no; // 작성자 번호(관리자만 가능)
	

	public Notice() {}
	

	
	
}
