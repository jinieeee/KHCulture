package com.kh.khculture.admin.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.khculture.admin.model.service.AdminService;
import com.kh.khculture.admin.model.vo.Search;
import com.kh.khculture.member.model.vo.Member;

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
	public String memberList(@RequestParam(value="memberRole", required=false) Integer memberRole,
							 @RequestParam(value="accLockYN", required=false) String accLockYN,
							 @RequestParam(value="accSecessionYN", required=false) String accSecessionYN,
							 @RequestParam(value="searchDate", required=false) String searchDate,
							 @RequestParam(value="searchKeyword", required=false) String searchKeyword,
							 @RequestParam(value="page", required=false) Integer page,
							 Model model) throws ParseException {
		// log.info("{}", memberList);
		// log.info("{}", memberRole + accLockYN + accSecessionYN + searchDate + searchKeyword + page);
		// log.info("{}", page);
		int authorityCode = (memberRole == null || memberRole == 0 || memberRole.toString().equals("")) ? 0 : memberRole == 1 ? 1 : 2;
		page = (page == null || page.toString().equals("") || page == 0) ? 1: page;
		accLockYN =  accLockYN == null || accLockYN.equals("전체")? null: accLockYN;
		accSecessionYN = accSecessionYN == null || accSecessionYN.equals("전체")? null: accSecessionYN;
		Date date = searchDate == null || searchDate.equals("")? new Date(): (new SimpleDateFormat("yyyy-MM-dd")).parse(searchDate);
		
		Search search = new Search();
		
		search.setAuthorityCode(authorityCode);
		search.setAccLockYN(accLockYN);
		search.setAccSecessionYN(accSecessionYN);
		search.setSearchKeyword(searchKeyword);
		search.setPage(page);
		
		Map<String, Object> result = adminService.getAllMemberList(search);
		
		model.addAttribute("search", search);
		model.addAttribute("pi", result.get("pi"));
		model.addAttribute("memberList", result.get("memberList"));
		model.addAttribute("searchListCount", result.get("searchListcount"));
		model.addAttribute("listCount", result.get("listCount"));
		
		return "/admin/memberList";
	}
}
