package com.kh.khculture.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/common")
public class CommonController {
	@RequestMapping("denied")
	public String requestDenied(RedirectAttributes rttr) {
		rttr.addFlashAttribute("msg", "요청이 거절되었습니다. 로그인 후 관리자에게 문의하세요.");
		return "/fragments/error";
	}
}
