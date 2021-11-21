package com.kh.khculture.notice.model.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.khculture.notice.model.vo.Notice;
import com.kh.khculture.notice.model.vo.Search;


@Mapper
public interface NoticeMapper {

	/* No.1
	//총 게시글 조회
	List<Notice> findAllNotice();
	*/
	
	//1. 게시글 총 개수
	int getListCount();
	int getcountentListCount(String searchValue);
	//2. 페이징된 게시글 수
	List<Notice> selectList(int startRow, int endRow);
	List<Notice> selectContentList(String searchValue,int startRow, int endRow);
	//3. 선택한 게시글
	Notice selectNotice(int n_no);
	//4. 게시글 작성
	int noticeInsert(Notice notice);
	//5. 게시글 수정 
	int noticeUpdate(Notice uptNotice);
	//6. 게시글 삭제
	int noticeDelete(Notice deleteNotice);
	
	
	
}
