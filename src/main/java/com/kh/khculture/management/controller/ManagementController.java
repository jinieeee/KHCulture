package com.kh.khculture.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ManagementController {
	
	@GetMapping("/lecturelist")
	public String lectureList() {
		return "management/lecturelist";
	}
	
	@GetMapping("/lectureregist")
	public String lectureRegist() {
		return "management/lectureregist";
	}
	@PostMapping("/registserver")
	public String lectureRegistServer() {
		
		return "redirect:/";
	}
}
