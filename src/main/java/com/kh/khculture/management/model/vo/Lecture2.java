package com.kh.khculture.management.model.vo;

import lombok.Data;

@Data
public class Lecture2 {
	private int lNo; //강의번호
	private String lTitle; //강의제목
	private String lContent; //강의내용
	private String lThumbnail; //강의 대표 이미지
	private String lcName; //카테고리
	private String ltName; //강좌 대상
}
