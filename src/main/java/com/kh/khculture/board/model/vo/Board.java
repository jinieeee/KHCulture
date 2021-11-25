package com.kh.khculture.board.model.vo;

import java.util.Date;

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
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
	private Date b_enroll_date;
//	private String b_enroll_date;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
	private Date b_modify_date;
//	private String b_modify_date;
	private String b_status;
	private int b_count; // 조회수
	private int b_star; //좋아요
	private double sum_b_star;
	
	private LectureOpen lectureOpen;
	
	public Board() {}


	

}
