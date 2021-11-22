package com.kh.khculture.management.model.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class LectureOpen3 {
	private int lrNo;
	private int lrCapacity;
	private int lrCount;
	private Date lrStartDate;
	private Date lrEndDate;
	private int lrNumber;
	private String lrDay;
	private String lrStartTime;
	private String lrEndTime;
	private Date lrEnrollDate;
	private Date lrModifyDate;
	private String lrStatus;
	private int instructorNo;
	private int lNo;
	private String lrFee;
	private String lcName;
	private String ltName;
	private String lTitle;
	private String instructorName;
}
