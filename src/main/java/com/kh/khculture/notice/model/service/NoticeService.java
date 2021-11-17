package com.kh.khculture.notice.model.service;

import java.util.List;

import com.kh.khculture.notice.model.vo.Notice;
import com.kh.khculture.notice.model.vo.PageInfo;

public interface NoticeService {
	
	//No.1 List<Notice> findAllNotice();

	//1. 게시글 총 개수
	int getListCount();
	//2. 페이징된 게시글 들
	List<Notice> selectList(PageInfo pi);


	

}
