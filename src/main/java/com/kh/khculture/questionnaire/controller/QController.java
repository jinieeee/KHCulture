package com.kh.khculture.questionnaire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/questionnaire")
public class QController {
	
	@GetMapping("/list")
	public String findList() {
		return "questionnaire/list";
	}

}
