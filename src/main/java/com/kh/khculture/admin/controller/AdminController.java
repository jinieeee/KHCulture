package com.kh.khculture.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.khculture.admin.model.service.AdminService;
import com.kh.khculture.admin.model.vo.Search;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/admin")
public class AdminController {

	private AdminService adminService;
	
	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	// 전체회원 조회
	@GetMapping("memberList")
	public String memberList(@RequestParam(value="memberRole", required=false) String memberRole,
							 @RequestParam(value="page", required=false) Integer page, Model model) {
		// log.info("{}", memberList);
		// log.info("{}", memberRole); 일반요청 : null, 전체 : 0, 일반회원 : member, 관리자 : admin

		// 검색조건
		Search search = new Search();
		int authorityCode = (memberRole == null || memberRole.equals("전체") || memberRole.equals("")) ? 0 : memberRole.equals("일반회원") ? 1 : 2;
		page = (page == null || page.toString().equals("")) ? 1: page;
		search.setAuthorityCode(authorityCode);
		search.setPage(page);

		Map<String, Object> result = adminService.getAllMemberList(search);

		model.addAttribute("memberList", result.get("memberList"));
		model.addAttribute("pi", result.get("pi"));
		model.addAttribute("authorityCode", authorityCode);
		model.addAttribute("listCount", result.get("listCount"));
		return "/admin/memberList";
	}
}
