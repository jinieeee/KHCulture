package com.kh.khculture.main.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.khculture.lecture.model.vo.LectureOpen;
import com.kh.khculture.main.model.vo.MainImage;

@Mapper
public interface MainMapper {
	
	// 추천 강좌 목록
	List<LectureOpen> selectRecommendList();
	
	// 이미지슬라이드용 메인이미지
	List<MainImage> selectAllMainImage();
	
}
