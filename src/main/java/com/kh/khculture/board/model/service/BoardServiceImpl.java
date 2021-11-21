package com.kh.khculture.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.khculture.board.model.dao.BoardMapper;
import com.kh.khculture.board.model.vo.PageInfo;
import com.kh.khculture.board.model.vo.board;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	private BoardMapper boardMapper;
	
	@Autowired
	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}
	@Override
	public int getListCount() {
		return boardMapper.getListCount();
	}

	@Override
	public int getContentListCount() {
		return 0;
	}
	@Override
	public List<board> selectList(PageInfo pi) {
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() +1;
		int endRow = startRow + pi.getBoardLimit() -1;
		return boardMapper.selectList(startRow,endRow);
	}

}
