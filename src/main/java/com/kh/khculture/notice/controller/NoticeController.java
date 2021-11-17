package com.kh.khculture.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
			//System.out.println("listCount : " + listCount);
		
		PageInfo pi = new PageInfo( page , listCount, 10,3);
			//System.out.println("pi : "+pi);
			//System.out.println(pi.getMaxPage());
		
		int startRow = (pi.getPage() -1)* pi.getBoardLimit() +1;
		int endRow = startRow +pi.getBoardLimit() -1;
		
		//System.out.println("test: "+startRow);
		//System.out.println(endRow);
		
		List<Notice> noticeList = noticeService.selectList(startRow,endRow);
		//	System.out.println("noticeList : " + noticeList);
		//	System.out.println(noticeList.get(0));
			
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("pi", pi);
		
		return "notice/noticeList";
	}
	
	@GetMapping("/detail.do")
	public ModelAndView noticeDetail( ModelAndView mv, @RequestParam int n_no) {
	
		System.out.println(n_no);
		Notice n = noticeService.selectNotice(n_no);
		System.out.println(n);
		
		mv.addObject("notice",n);
		mv.setViewName("notice/noticeDetail");
		
		return mv;
		
	}
	@GetMapping("/insert")
	public String noticeInsert(Model model) {
		
		return "notice/noticeInsert";
		
	}
}

