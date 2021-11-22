package com.kh.khculture.main.model.service;

import java.util.List;
import java.util.Map;

import com.kh.khculture.main.model.vo.MainImage;

public interface MainService {
	// 추천 강좌 목록
	Map<String, Object> selectRecommendList();

	// 이미지슬라이드용 메인이미지
	List<MainImage> selectAllMainImage();
	
}
