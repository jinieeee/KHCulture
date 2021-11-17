package com.kh.khculture.notice.model.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.khculture.notice.model.vo.Notice;
import com.kh.khculture.notice.model.vo.PageInfo;


@Mapper
public interface NoticeMapper {

	/* No.1
	//총 게시글 조회
	List<Notice> findAllNotice();
	*/
	// 게시글 총 갯수
	int getListCount();
	// 페이징된 게시글 수
	List<Notice> selectList(PageInfo pi);
}
