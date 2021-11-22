package com.kh.khculture.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.khculture.board.model.service.BoardService;
import com.kh.khculture.board.model.vo.PageInfo;
import com.kh.khculture.board.model.vo.board;
import com.kh.khculture.lecture.model.service.LectureService;
import com.kh.khculture.lecture.model.vo.Lecture;
import com.kh.khculture.member.model.dao.MemberMapper;
import com.kh.khculture.member.model.vo.UserImpl;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	private BoardService boardService;
	private LectureService lectureService;
	
	@Autowired
	public BoardController(BoardService boardService, LectureService lectureService) {
		this.boardService = boardService;
		this.lectureService=lectureService;
	}

	
	@GetMapping("boardList")
	public String boardList(Model model, @RequestParam(value="page", defaultValue="1") int page) {
		
		int listCount = boardService.getListCount();
	//	System.out.println("총 게시글 개수 : " + listCount);
		
		PageInfo pi = new PageInfo(page, listCount, 10,3);
		List<board> boardList = boardService.selectList(pi);
	//	System.out.println("후기게시판리스트 = " + boardList);
	
		model.addAttribute("boardList",boardList);
		model.addAttribute("pi",pi);
		
		return "board/boardList";
	}
	
	//로그인 user의 정보가 들어옴
	@GetMapping("insert")
	public String boardInsertView(Model model,@AuthenticationPrincipal UserImpl userImpl){
		// id의 값이 들어오면... memberMapper로 로그인한 유저인지 확인..?
		// log.info("로그인 계정 : {}", member);
		log.info("로그인 계정 : {}",userImpl);
	//	List<Lecture> lectureList = lectureService.selectList(userImpl.getId());
				
		return "board/boardInsert";
	}
}
