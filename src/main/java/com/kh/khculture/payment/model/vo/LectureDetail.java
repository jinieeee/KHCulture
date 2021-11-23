package com.kh.khculture.payment.model.vo;

import java.util.Date;

import lombok.Data;

@Data
public class LectureDetail {
	
	private int lrNo;
	private String lThumbnail;
	private String lTitle;
	private String lContent;
	private String lcName;
	private String ltName;
	
	private int lrCapacity;
	private int lrCount;
	private Date lrStartDate;
	private Date lrEndDate;
	private int lrNumber;
	private String lrDay;
	private String lrStartTime;
	private String lrEndTime;
	private String lrFee;
	
	private String instructorName;
	private String profilePhoto;
	private String birthDate;
	private String instructorPhone;
	private String career;
	
}
