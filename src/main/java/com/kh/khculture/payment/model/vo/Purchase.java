package com.kh.khculture.payment.model.vo;

import java.util.Date;

import com.kh.khculture.lecture.model.vo.LectureOpen;

import lombok.Data;

@Data
public class Purchase {
	private int rNo; //주문번호
	private int lrNo; //강의등록번호
	private String lbStatus; //결제상태
	private Date lbEnrollDate; //강의구매일
	private Date lbModifyDate; //강의취소일
	private LectureOpen lectureOpen; //강의오픈
}
