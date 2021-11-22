package com.kh.khculture.lecture.model.service;

import java.util.List;
import java.util.Map;

import com.kh.khculture.lecture.model.vo.Lecture;
import com.kh.khculture.lecture.model.vo.Search;

public interface LectureService {

	Map<String, Object> selectLectureList(Search search);

	List<Integer> findYear();

	

	

}
