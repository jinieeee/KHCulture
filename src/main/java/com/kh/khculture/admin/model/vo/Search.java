package com.kh.khculture.admin.model.vo;

import java.util.Date;

import lombok.Data;

@Data
public class Search {
	private int authorityCode;
	private String accLockYN;
	private String accSecessionYN;
	private Date searchDate;
	private String searchKeyword;
	private int page;
	private int startRow;
	private int endRow;
	
	public Search() {}

}
