package com.kh.khculture.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.khculture.notice.model.service.NoticeService;
import com.kh.khculture.notice.model.vo.Notice;
import com.kh.khculture.notice.model.vo.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	private NoticeService noticeService;
	
	@Autowired
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;	
	}

	//전체 List + paging처리
	@GetMapping("noticeList")
	public String noticeList(  Model model, @RequestParam(value="page" , defaultValue="1") int page
								,@RequestParam(value="searchValue", required = false) String searchValue) {
		
		
		int	listCount = noticeService.getListCount(searchValue);
		System.out.println(listCount);
		
		PageInfo pi = new PageInfo( page , listCount, 10,3);
		List<Notice> noticeList = noticeService.selectList(pi,searchValue); 
		
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("pi", pi);
		
		return "notice/noticeList";
	}
	
	@GetMapping("detail.do")
	public ModelAndView noticeDetail( ModelAndView mv, @RequestParam int n_no) {
	
		//System.out.println(n_no);
		Notice n = noticeService.selectNotice(n_no);
		//System.out.println("selectNotice 의 n  = "+n);
		//System.out.println("selectNotice 의 n  = "+n.getN_enroll_date());
		mv.addObject("notice",n);
		mv.setViewName("notice/noticeDetail");
		
		return mv;
		
	}
	
	//게시글 insert
	@GetMapping("insert") 
	public String noticeInsertPage(Model model) {
		return "notice/noticeInsert";	//HTMl 경로	
	}
	@PostMapping("insert")
	public String noticeInsert(Notice Newnotice) {
	//	System.out.println("Newnotice = " + Newnotice);
		noticeService.noticeInsert(Newnotice);
		return "redirect:/notice/noticeList";
	}
	
	
	
	//게시글 수정하기
	@GetMapping("updateView")
	public String noticeUpdatePage(Model model,@RequestParam("n_no") int n_no) {
	//	System.out.println(n_no);
		Notice n = noticeService.selectNotice(n_no);
	//	System.out.println(n);
		model.addAttribute("notice", n);
		return "notice/noticeUpdate";
	}
	//?n_no=22
	@PostMapping("update")
	public String noticeUpdate(Notice uptNotice) {
		noticeService.noticeUpdate(uptNotice);
	//	System.out.println("uptNotice = "+uptNotice);
		return "redirect:/notice/detail.do?n_no="+ uptNotice.getN_no();
	}
	
	@PostMapping("delete")
	public String noticeDelete(Notice deleteNotice, @RequestParam("n_no") int n_no) {
		noticeService.deleteNotice(deleteNotice);
		return "redirect:/notice/noticeList";
	}
	

}

