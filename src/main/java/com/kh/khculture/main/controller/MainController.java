package com.kh.khculture.main.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.khculture.lecture.model.vo.Search;
import com.kh.khculture.main.model.service.MainService;
import com.kh.khculture.main.model.vo.MainImage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {
	private MainService mainService;
	
	@Autowired
	public MainController(MainService mainService) {
		this.mainService = mainService;
	}
	@GetMapping(value= {"/", "/main"})
	public String main(Model model) {
		List<MainImage> mainImages = mainService.selectAllMainImage();
		model.addAttribute("mainImages", mainImages);
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
	
	@GetMapping(value="/main/recommendList", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> selectRecommendList(Model model) {
		return mainService.selectRecommendList();
	}
}
