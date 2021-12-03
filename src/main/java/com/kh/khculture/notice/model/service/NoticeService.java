package com.kh.khculture.notice.model.service;

import java.util.Map;

import com.kh.khculture.notice.model.vo.Notice;

public interface NoticeService {
	 
	
	//1. 게시글LIst
	Map<String, Object> noticeList(int page, String searchValue);
	//3. 선택한 게시물
	Notice selectNotice(int n_no);
	//4. 게시글 쓰기
	int noticeInsert(Notice notice);
	//5. 게시글 수정하기
	int noticeUpdate(Notice uptNotice);
	//6. 게시글 삭제하기
	int deleteNotice(Notice deleteNotice);
	
	
	
	
	


	

}
