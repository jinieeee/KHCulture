package com.kh.khculture.instructor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.khculture.instructor.model.service.InstructorService;
import com.kh.khculture.instructor.model.vo.Instructor;

@Controller
@RequestMapping("/instructor")
public class InstructorController {
	
	private InstructorService instructorService;
	
	@Autowired
	public InstructorController(InstructorService instructorService) {
		this.instructorService = instructorService;
	}
	
	@GetMapping("/list")
	public String findList(Model model) {
		
		List<Instructor> instructorList = instructorService.getList();
		
		model.addAttribute("instructorList", instructorList);
		
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
