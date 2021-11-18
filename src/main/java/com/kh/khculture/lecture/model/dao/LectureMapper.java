package com.kh.khculture.lecture.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.khculture.lecture.model.vo.LectureOpen;

@Mapper
public interface LectureMapper {

	List<LectureOpen> selectLectureList();
	
}
