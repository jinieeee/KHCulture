package com.kh.khculture.notice.model.vo;

public class Search {
	private String searchValue;
	
	public Search() {}


	public Search(String searchValue) {
		super();
		this.searchValue = searchValue;
	}


	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	@Override
	public String toString() {
		return "Search [searchValue=" + searchValue + "]";
	}
	
	
}
