package com.kh.khculture.lecture.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.khculture.lecture.model.service.LectureService;
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
	
//	김현주
	@GetMapping("schedule")
	public String getLectureScheduleList() {
		return "lectureList/lectureSchedule";
	}
	
	@GetMapping(value="list", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> selectLectureList(Model model, @ModelAttribute Search search) {
		return lectureService.selectLectureList(search);
	}
	
	@GetMapping(value="year", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<Integer> findYear() {
		
		return lectureService.findYear();
		
	}
	
	
	
	@GetMapping("search")
	public String getLectureList(Model model) {
		Search search = new Search();
		model.addAttribute("search", search);
		return "lectureList/lectureSearch";
	}
// 김현주
	
}
