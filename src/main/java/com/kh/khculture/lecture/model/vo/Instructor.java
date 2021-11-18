package com.kh.khculture.lecture.model.vo;

import lombok.Data;

@Data
public class Instructor {
	private int instructorNo; //강사번호
	private String instructorName; //강사이름
	private String profilePhoto; //프로필사진
	private String birthDate; //생년월일
	private String instructorPhone; //강사연락처
	private String career; //경력
}
