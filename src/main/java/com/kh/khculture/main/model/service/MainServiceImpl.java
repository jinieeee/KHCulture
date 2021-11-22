package com.kh.khculture.main.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.khculture.lecture.model.vo.LectureOpen;
import com.kh.khculture.main.model.dao.MainMapper;
import com.kh.khculture.main.model.vo.MainImage;

@Service
public class MainServiceImpl implements MainService{

	private MainMapper mainMapper;
	
	@Autowired
	public MainServiceImpl(MainMapper mainMapper) {
		this.mainMapper = mainMapper;
	}
	
	// 추천 강좌 목록
	@Override
	public Map<String, Object> selectRecommendList() {
		Map<String, Object> returnMap = new HashMap<>();
		List<LectureOpen> recommendList = mainMapper.selectRecommendList(); 
		returnMap.put("recommendList", recommendList);
		return returnMap;
	}

	// 이미지슬라이드용 메인이미지
	@Override
	public List<MainImage> selectAllMainImage() {
		List<MainImage> mainImages = new ArrayList<>();
		mainImages = mainMapper.selectAllMainImage();
		return mainImages;
	}

}
