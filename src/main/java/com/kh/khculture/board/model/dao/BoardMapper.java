package com.kh.khculture.board.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.khculture.board.model.vo.board;

@Mapper
public interface BoardMapper {

	int getListCount();

	List<board> selectList(int startRow, int endRow);

}
