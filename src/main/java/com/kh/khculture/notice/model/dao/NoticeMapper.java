package com.kh.khculture.notice.model.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.khculture.notice.model.vo.Notice;


@Mapper
public interface NoticeMapper {

	/* No.1
	//총 게시글 조회
	List<Notice> findAllNotice();
	*/
	
	//1. 게시글 총 갯수
	int getListCount();
	//2. 페이징된 게시글 수
	List<Notice> selectList(int startRow, int endRow);
	//3. 선택한 게시글
	Notice selectNotice(int n_no);
	//4. 게시글 작성
	int noticeInsert(Notice notice);
	
	
}
