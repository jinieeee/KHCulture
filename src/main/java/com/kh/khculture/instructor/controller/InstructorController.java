package com.kh.khculture.instructor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/instructor")
public class InstructorController {
	
	@GetMapping("/list")
	public String findList() {
		return "instructor/list";
	}
	
	@GetMapping("/regist")
	public String registList() {
		return "instructor/regist";
	}	
	
	@GetMapping("/detail")
	public String detailList() {
		return "instructor/detail";
	}

	@GetMapping("/update")
	public String updateList() {
		return "instructor/update";
	}
}
