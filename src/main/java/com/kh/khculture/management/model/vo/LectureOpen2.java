package com.kh.khculture.management.model.vo;

import java.util.Date;

import lombok.Data;

@Data
public class LectureOpen2 {
	private int lrNo;
	private int lrCapacity;
	private int lrCount;
	private String lrStartDate;
	private String lrEndDate;
	private int lrNumber;
	private String lrDay;
	private String lrStartTime;
	private String lrEndTime;
	private Date lrEnrollDate;
	private Date lrModifyDate;
	private String lrStatus;
	private int instructorNo;
	private int lNo;
	private int lrFee;
}
