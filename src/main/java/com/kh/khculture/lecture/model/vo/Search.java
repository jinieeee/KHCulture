package com.kh.khculture.lecture.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
public class Search {
	private String category;
	private String target;
	private String status;
	private String year;
	private String month;
	private String day;
	private String time;
	private String typing;
	private Date today;
	private String arrayStand;
	private int page;
	private int startRow;
	private int endRow;
}
