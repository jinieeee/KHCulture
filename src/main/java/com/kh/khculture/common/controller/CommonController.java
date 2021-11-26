package com.kh.khculture.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class CommonController {
	@RequestMapping("denied")
	public String requestDenied(Model model) {
		model.addAttribute("text", "접근 권한이 없습니다. 로그인 후 관리자에게 문의하세요.");
		return "/fragments/error";
	}
	
	@RequestMapping("/authEntry")
	public String authEntry() {
		return "/fragments/authEntry";
	}
}
