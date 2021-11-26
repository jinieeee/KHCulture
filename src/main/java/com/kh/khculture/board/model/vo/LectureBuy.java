package com.kh.khculture.board.model.vo;

import java.util.List;


import com.kh.khculture.lecture.model.vo.LectureOpen;

import lombok.Data;
@Data
public class LectureBuy {
	private int r_no;
	private int lr_no;
	private String lb_status;
	
	private List<LectureOpen> lectureOpen;
	
	public LectureBuy() {}
}
