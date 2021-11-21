package com.kh.khculture.lecture.model.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class LectureOpen implements Comparable<LectureOpen>{
	private int lrNo; //강의등록번호
	private int lrCapacity; //정원
	private int lrCount; //신청인 수
	private String lrFee; //수강료
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
	private Date lrStartDate; //수강시작일
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
	private Date lrEndDate; //수강 종료일
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
	private Date lrParDate; //수업일
	private int lrNumber; //강의횟수
	private String lrDay; //수강요일
	private String lrStartTime; //수강시작시간
	private String lrEndTime; //수강종료시간
	private Lecture lecture; //강의
	private Instructor instructor;
//
//	public LectureOpen(LectureOpen lc) {
//		this.lrNo = lc.getLrNo();
//		this.lrCapacity = lc.getLrCapacity();
//		this.lrCount = lc.getLrCount();
//		this.lrFee = lc.getLrFee();
//		this.lrStartDate = lc.getLrStartDate();
//		this.lrEndDate = lc.getLrEndDate();
//		this.lrParDate = lc.getLrParDate();
//		this.lrNumber = lc.getLrNumber();
//		this.lrDay = lc.getLrDay();
//		this.lrStartTime = lc.getLrStartTime();
//		this.lrEndTime = lc.getLrEndTime();
//		this.lecture = lc.getLecture();
//		this.instructor = lc.getInstructor();
//	}
	@Override
	public int compareTo(LectureOpen o) {
		// TODO Auto-generated method stub
		return lrParDate.compareTo(o.getLrParDate());
	}
}
