package com.kh.khculture.notice.model.vo;

public class PageInfo {
	private int page ;		//요청페이지
	private int listCount;	// 게시글 수 ( DB에서 조회한 값이 들어갈것 )
	private int pageLimit;	// 한 페이지 하단에 보여질 페이징바의 개수 - 10개
	private int boardLimit; // 한 페이지에 보여질 게시글 최대 수 - 10개
	private int maxPage;	// 전체 페이지에서 가장 마지막 페이지 - 11페이지
	private int startPage;	// 한 페이지 하단에 보여질 페이징바의 시작 값 - 1페이지
	private int endPage; 	// 한 페이지 하단에 보여질 페이징바의 마지막값 - 10페이지
	
	public PageInfo() {}

	//페이징 처리 계산에 필요한 값을 받아 max/start/endPage 계산하여 설정하기
	public PageInfo(int page, int listCount, int pageLimit, int boardLimit) {
		super();
		this.page = page;
		this.listCount = listCount;
		this.pageLimit = pageLimit;
		this.boardLimit = boardLimit;
		
		this.maxPage =(int) Math.ceil((double)listCount / boardLimit);
		this.startPage = (page-1) / pageLimit * pageLimit +1;
		this.endPage = startPage + pageLimit -1;

		if(maxPage < endPage) {
			endPage = maxPage;
		}
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public int getBoardLimit() {
		return boardLimit;
	}

	public void setBoardLimit(int boardLimit) {
		this.boardLimit = boardLimit;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	@Override
	public String toString() {
		return "PageInfo [page=" + page + ", listCount=" + listCount + ", pageLimit=" + pageLimit + ", boardLimit="
				+ boardLimit + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
}
