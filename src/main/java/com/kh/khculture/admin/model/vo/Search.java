package com.kh.khculture.admin.model.vo;

import lombok.Data;

@Data
public class Search {
	private int authorityCode;
	private int page;
	private int startRow;
	private int endRow;
	
	public Search() {}
}
