package com.kh.khculture.notice.model.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.kh.khculture.notice.model.vo.Notice;

 
@Mapper
public interface NoticeMapper {


	//1. 게시글 총 개수
	int getListCount(String searchValue);
	//2. 페이징된 게시글 수
	List<Notice> selectList(Map<String, Object> map);
	//3. 선택한 게시글
	Notice selectNotice(int n_no);
	//4. 게시글 작성
	int noticeInsert(Notice notice);
	//5. 게시글 수정 
	int noticeUpdate(Notice uptNotice);
	//6. 게시글 삭제
	int noticeDelete(Notice deleteNotice);
	
	
	
}
