package com.kh.khculture.board.model.service;

import java.util.List;

import com.kh.khculture.board.model.vo.PageInfo;
import com.kh.khculture.board.model.vo.board;

public interface BoardService {

	int getListCount();
	int getContentListCount();
	List<board> selectList(PageInfo pi);
	

}
