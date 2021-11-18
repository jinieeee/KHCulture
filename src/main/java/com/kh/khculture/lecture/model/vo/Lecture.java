package com.kh.khculture.lecture.model.vo;

import lombok.Data;

@Data
public class Lecture {
	private int lNo; //강의번호
	private String lTitle; //강의제목
	private String lContent; //강의내용
	private String lThumbnail; //강의 대표 이미지
	private int lcNo; //카테고리
	private int ltNo; //강좌 대상
}
