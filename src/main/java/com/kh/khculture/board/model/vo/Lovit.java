package com.kh.khculture.board.model.vo;

import lombok.Data;

@Data
public class Lovit {
	
	//어떤 사람이 어떤 게시물을 좋아요 했는지 
	private int m_no;
	private int b_no;
	
	public Lovit() {}

	
	public Lovit(int m_no, int b_no) {
		super();
		this.m_no = m_no;
		this.b_no = b_no;
	}

}
