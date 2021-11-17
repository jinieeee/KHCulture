package com.kh.khculture.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.khculture.notice.model.service.NoticeService;
import com.kh.khculture.notice.model.vo.Notice;
import com.kh.khculture.notice.model.vo.PageInfo;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	private NoticeService noticeService;
	
	
	@Autowired
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;	
	}
	/*  No.1
	//전체 공지글
	@GetMapping("/noticeList")
	public ModelAndView findNoticeList(Model model) {
		List<Notice> noticeList = noticeService.getNoticeList();
		System.out.println("noticeList : "+noticeList);
		
		mv.addObject(noticeList);
		mv.setViewName("notice/noticeList");
		
		return mv;
	}
	
	*/
	
	//전체 List + paging처리
	@GetMapping("noticeList")
	public String noticeList(Model model, @RequestParam(value="page" , defaultValue="1") int page) {
		
		// 게시글 총 개수
		int listCount = noticeService.getListCount();
			System.out.println("listCount : " + listCount);
			
		/* public PageInfo(int page, int listCount, int pageLimit, int boardLimit)
		   int pageLimit;	// 한 페이지 하단에 보여질 페이징바의 개수
		   int boardLimit; // 한 페이지에 보여질 게시글 최대 수
		*/	
		PageInfo pi = new PageInfo( page , listCount, 10,3);
			System.out.println("pi : "+pi);
			System.out.println(pi.getMaxPage()); //3
			
			
		List<Notice> noticeList = noticeService.selectList(pi);
			System.out.println("noticeList : " + noticeList);
		//	System.out.println(noticeList.get(0));
			
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("pi", pi);
		
		return "notice/noticeList";
	}
}

