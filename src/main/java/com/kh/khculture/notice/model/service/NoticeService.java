package com.kh.khculture.notice.model.service;

import java.util.List;

import com.kh.khculture.notice.model.vo.Notice;
import com.kh.khculture.notice.model.vo.PageInfo;
import com.kh.khculture.notice.model.vo.Search;

public interface NoticeService {
	
	//No.1 List<Notice> findAllNotice();

	//1. 게시글 총 개수
	int getListCount(String searchValue);
	//2. 페이징된 게시글 들
	List<Notice> selectList(PageInfo pi, String searchValue);
	//3. 선택한 게시물
	Notice selectNotice(int n_no);
	//4. 게시글 쓰기
	int noticeInsert(Notice notice);
	//5. 게시글 수정하기
	int noticeUpdate(Notice uptNotice);
	//6. 게시글 삭제하기
	int deleteNotice(Notice deleteNotice);
	
	
	
	
	
	


	

}
