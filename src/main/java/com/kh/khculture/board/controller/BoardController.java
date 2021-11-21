package com.kh.khculture.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.khculture.board.model.service.BoardService;
import com.kh.khculture.board.model.vo.PageInfo;
import com.kh.khculture.board.model.vo.board;



@Controller
@RequestMapping("/board")
public class BoardController {
	private BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	
	@GetMapping("boardList")
	public String boardList(Model model, @RequestParam(value="page", defaultValue="1") int page) {
		
		int listCount = boardService.getListCount();
		System.out.println("총 게시글 개수 : " + listCount);
		
		PageInfo pi = new PageInfo(page, listCount, 10,3);
		List<board> boardList = boardService.selectList(pi);
		System.out.println("후기게시판리스트 = " + boardList);
	
		model.addAttribute("boardList",boardList);
		model.addAttribute("pi",pi);
		
		return "board/boardList";
	}
	
	@GetMapping("insert")
	public String boardInsertView(Model model){
			
		
		return "board/boardInsert";
	}
}
