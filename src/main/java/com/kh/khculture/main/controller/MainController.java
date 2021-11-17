package com.kh.khculture.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	
}
