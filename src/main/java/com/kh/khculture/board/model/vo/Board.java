package com.kh.khculture.board.model.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kh.khculture.lecture.model.vo.LectureOpen;

import lombok.Data;

@Data
public class Board {

	private int b_no;
	private String b_title;
	private String b_content;
	private int m_no;
	private int lr_no; //강의등록번호
	@JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")
	private Date b_enroll_date;
//	private String b_enroll_date;
	@JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")
	private Date b_modify_date;
//	private String b_modify_date;
	private String b_status;
	private int b_count; // 조회수
	private int b_star; //좋아요
	private double sum_b_star;
	
	private List<Reply> replyList;
	
	private LectureOpen lectureOpen;
	
	public Board() {}

	public Board(int b_no, String b_title, String b_content, int m_no, int lr_no, Date b_enroll_date,
			Date b_modify_date, String b_status, int b_count, int b_star, double sum_b_star, List<Reply> replyList,
			LectureOpen lectureOpen) {
		super();
		this.b_no = b_no;
		this.b_title = b_title;
		this.b_content = b_content;
		this.m_no = m_no;
		this.lr_no = lr_no;
		this.b_enroll_date = b_enroll_date;
		this.b_modify_date = b_modify_date;
		this.b_status = b_status;
		this.b_count = b_count;
		this.b_star = b_star;
		this.sum_b_star = sum_b_star;
		this.replyList = replyList;
		this.lectureOpen = lectureOpen;
	}

	


	

}
