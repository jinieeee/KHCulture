package com.kh.khculture.statistic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistics")
public class StatisticController {
	
	@GetMapping("/graph")
	public String statistic() {
		return "statistics/graph";
	}

}
