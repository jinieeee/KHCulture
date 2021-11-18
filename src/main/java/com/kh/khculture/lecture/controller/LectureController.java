package com.kh.khculture.lecture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.khculture.lecture.model.service.LectureService;
import com.kh.khculture.lecture.model.vo.LectureOpen;
import com.kh.khculture.lecture.model.vo.Search;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/lecture")
public class LectureController {
	private LectureService lectureService;
	
	@Autowired
	public LectureController(LectureService lectureService) {
		this.lectureService = lectureService;
	}
	
	@GetMapping("schedule")
	public String getLectureScheduleList() {
		return "lectureList/lectureSchedule";
	}
	
	@GetMapping(value="list", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<LectureOpen> findCategory(@ModelAttribute Search search) {
		return lectureService.selectLectureList();
		
	}
	
	@GetMapping("search")
	public String getLectureList() {
		return "lectureList/lectureSearch";
	}
	
}
