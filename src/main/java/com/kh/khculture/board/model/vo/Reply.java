package com.kh.khculture.board.model.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Reply {
	
	private int r_no;
	private String r_content;
	@JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")
	private Date r_enroll_date;
	private Date r_modify_date;
	private int n_no;
	private int b_no;
	private String r_status;
	private int m_no;
	private String name;
	
	public Reply() {}

	


	public Reply(int r_no,String r_content, Date r_enroll_date, Date r_modify_date, int b_no,
			String r_status, int m_no, String name) {
		super();
		this.r_no = r_no;
		this.r_content = r_content;
		this.r_enroll_date = r_enroll_date;
		this.r_modify_date = r_modify_date;
		this.b_no = b_no;
		this.r_status = r_status;
		this.m_no = m_no;
		this.name = name;
	}




	public Reply(int r_no, String r_content, int b_no, int m_no, String name) {
		super();
		this.r_no = r_no;
		this.r_content = r_content;
		this.b_no = b_no;
		this.m_no = m_no;
		this.name = name;
	}




}
