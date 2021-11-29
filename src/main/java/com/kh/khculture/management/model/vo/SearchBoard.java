package com.kh.khculture.management.model.vo;

import lombok.Data;

@Data
public class SearchBoard {
	
	private String searchCondition;
	private String searchValue;
	private int startRow;
	private int endRow;
	
}
