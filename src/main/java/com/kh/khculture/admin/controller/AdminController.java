package com.kh.khculture.admin.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		log.info("{}", result.get("pi"));
		model.addAttribute("memberList", result.get("memberList"));
		model.addAttribute("searchListCount", result.get("searchListcount"));
		model.addAttribute("listCount", result.get("listCount"));
		
		return "/admin/memberList";
	}
	
	@PostMapping("memberList/pwdReset")
	public String pwdReset(@RequestParam(value="arr") String arr, RedirectAttributes rttr) {
		String[] sArr = arr.split(",");
		List<Integer> mnoList = new ArrayList<>();
		
		for(String s : sArr) {
			mnoList.add(Integer.parseInt(s));
		}
		int result = adminService.pwdReset(mnoList);
		if(result > 0) {
			rttr.addFlashAttribute("msg", "비밀번호 초기화 성공!");
		} else {
			rttr.addFlashAttribute("msg", "비밀번호 초기화 실패!");			
		}
		return "redirect:/admin/memberList";
	}
	
	@PostMapping("memberList/deleteAcc")
	public String deleteAcc(@RequestParam(value="arr") String arr, RedirectAttributes rttr) {
		String[] sArr = arr.split(",");
		List<Integer> mnoList = new ArrayList<>();
		
		for(String s : sArr) {
			mnoList.add(Integer.parseInt(s));
		}
		int result = adminService.deleteAcc(mnoList);
		if(result > 0) {
			rttr.addFlashAttribute("msg", mnoList.size() + "개 계정 삭제 성공!");
		} else {
			rttr.addFlashAttribute("msg", mnoList.size() + "개 계정 삭제 실패!");			
		}
		
		
		return "redirect:/admin/memberList";
	}
	
	@PostMapping("memberList/roleUpdate")
	public String roleUpdate(@RequestParam(value="arr") String arr, RedirectAttributes rttr) {
		String[] sArr = arr.split(",");
		List<Integer> mnoList = new ArrayList<>();
		
		for(String s : sArr) {
			mnoList.add(Integer.parseInt(s));
		}
		int result = adminService.roleUpdate(mnoList);
		if(result > 0) {
			rttr.addFlashAttribute("msg", mnoList.size() + "개 계정을 관리자 등급으로 변경했습니다");
		} else {
			rttr.addFlashAttribute("msg", mnoList.size() + "개 계정을 관리자 등급으로 변경하는데 실패했습니다");			
		}
		return "redirect:/admin/memberList";
	}
}
