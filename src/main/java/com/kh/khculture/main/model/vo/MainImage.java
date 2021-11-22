package com.kh.khculture.main.model.vo;

import lombok.Data;

@Data
public class MainImage {
	private int miNo;			// 이미지번호
	private int mno;			// 회원번호
	private String miRoute;		// 이미지경로
	private String miOrigin;	// 원본파일명
	private String miRename;	// 수정파일명
	private int refNo;			// 참조 공지사항 번호
	private String miStatus;	// 상태
	
	public MainImage() {}
}
