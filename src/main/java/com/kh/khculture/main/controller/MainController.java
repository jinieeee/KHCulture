package com.kh.khculture.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.khculture.lecture.model.vo.Search;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {
	
	@GetMapping(value= {"/", "/main"})
	public String main() {
		return "main";
	}
	@PostMapping(value="/")
	public String redirectMain() {
		return "redirect:/";
	}
	
	@PostMapping(value="/main/search")
	public String submitSearch(Search search, Model model) {
//		log.info("{}", search == null);
//		log.info("{}", search.getCategory() == null);
//		log.info("{}", search.getCategory() == "");
		
		model.addAttribute("search", search);
		return "/lectureList/lectureSearch";
	}
}
