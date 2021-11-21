package com.kh.khculture.main.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.khculture.lecture.model.vo.LectureOpen;

@Mapper
public interface MainMapper {
	
	// 추천 강좌 목록
	List<LectureOpen> selectRecommendList();
	
}
