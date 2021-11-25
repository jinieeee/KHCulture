package com.kh.khculture.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.khculture.board.model.service.BoardService;
import com.kh.khculture.board.model.vo.Board;
import com.kh.khculture.board.model.vo.Lovit;
import com.kh.khculture.board.model.vo.PageInfo;
import com.kh.khculture.board.model.vo.Receipt;
import com.kh.khculture.lecture.model.vo.LectureOpen;
import com.kh.khculture.member.model.vo.UserImpl;
import com.kh.khculture.notice.model.vo.Notice;

import lombok.extern.slf4j.Slf4j;


@Slf4j
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
	//	System.out.println("총 게시글 개수 : " + listCount);
		
		PageInfo pi = new PageInfo(page, listCount, 10,3);
		List<Board> boardList = boardService.selectList(pi);
	//	System.out.println("후기게시판리스트 = " + boardList);
	
		//랭킹 구현할 board image
		List<Board> rankList = boardService.ranktList();
		
		log.info("rankList : {} ",rankList);
		
		model.addAttribute("rankList",rankList);
		model.addAttribute("boardList",boardList);
		model.addAttribute("pi",pi);
		
		return "board/boardList";
	}
	

	@GetMapping("detail.do")
	public ModelAndView noticeDetail( ModelAndView mv, @RequestParam int b_no, @AuthenticationPrincipal UserImpl userImpl) {
	
		//System.out.println(n_no);
		Board b = boardService.selectBoard(b_no,userImpl.getMno());
		//System.out.println("selectNotice 의 n  = "+n);
		//System.out.println("selectNotice 의 n  = "+n.getN_enroll_date());
		// Mno로만 조회하면 되는데 굳이 b_no를 매개변수에 넣은 이유 : 검색결과
		Lovit l = boardService.selectLovit(b_no,userImpl.getMno()); // mno=01이 눌렀는지 select하고 detail에 뿌려준다.
		log.info("BoardDetail : {} ",b); // BoardDetail : Board(b_no=12, b_title=user3의 후기3,
		log.info("Lovit : {}" , l); // Lovit : Lovit(m_no=4, b_no=12)
		mv.addObject("Board",b);
		mv.addObject("Lovit",l);
		mv.setViewName("board/boardDetail");
		
		return mv;
		
	}
	
	
	//로그인 user의 정보가 들어옴
	// /board/insert?user_name=
	@GetMapping("insertView")
	public String boardInsertView(Model model,@AuthenticationPrincipal UserImpl userImpl){
		// id의 값이 들어오면... memberMapper로 로그인한 유저인지 확인..?
	//	 log.info("로그인 계정 : {}", member);
		log.info("22로그인 계정 : {}",userImpl.getMno());
		
		//로그인한 user의 mNo를 가지고 강좌를 불러옴
		//만약 mNo가 들은 강좌가 없으면 -> alert창으로 들은 강좌가 없다고 안내..
		List<LectureOpen> userLectureList = boardService.userLectureList(userImpl.getMno());
		if(userLectureList.isEmpty()) {
			System.out.println("강좌없다유------------ 강좌신청 페이지로 이동");
			
			return "redirect:boardList";
		}else {
			//log.info("1userLectureList 두그두그 :{}" ,userLectureList.get(0).getLectureBuy().get(0).getLectureOpen().get(0).getLecture().getLThumbnail());
			//log.info("2userLectureList 두그두그 :{}" ,userLectureList.get(1).getLectureBuy().get(0).getLectureOpen().get(0).getLecture().getLThumbnail());
			log.info("3userLectureList 두그두그 :{}" ,userLectureList);
			//model.addAttribute("userImpl",userImpl);
			model.addAttribute("lecture",userLectureList);
			System.out.println("후기남길수있다유");
			return "board/boardInsert";
		}
	}
	
	@PostMapping("insert")
	public String boardInsert(Board NewBoard,@AuthenticationPrincipal UserImpl userImpl) {
		NewBoard.setM_no(userImpl.getMno());
		log.info("NewBoard : {}",NewBoard);
		boardService.boardInsert(NewBoard);
		return "redirect:/board/boardList";
		
	}
	
	
	//like
	@PostMapping("likeinsert")
	@ResponseBody 
	public void likeinsert(@RequestParam int bno,@AuthenticationPrincipal UserImpl userImpl,HttpServletResponse response) throws IOException {
		//bno와 mno를 LOVIT 테이블에 insert해주고, mno가 로그인했을때 좋아요 표시 유지,
		//그렇다면 star은??? - 
		log.info("bno : {}",bno); //12
		int result = boardService.likeInsert(userImpl.getMno(),bno); //1
	
		System.out.println("등록: "+result);
		
		
		PrintWriter out = response.getWriter();
		
		if(result>0) {
			out.write("success");
			
		}else {
			out.write("fail");
		}
		
		out.flush();
		out.close();
		
	}
	
	//like
		@PostMapping("likedelete")
		@ResponseBody 
		public void likedelete(@RequestParam int bno,@AuthenticationPrincipal UserImpl userImpl,HttpServletResponse response) throws IOException {
			//bno와 mno를 LOVIT 테이블에 insert해주고, mno가 로그인했을때 좋아요 표시 유지,
			//그렇다면 star은??? - 
			log.info("bno : {}",bno); //12
			int result = boardService.likedelete(userImpl.getMno(),bno); //1
		
			System.out.println("삭제: "+ result);
			
			
			PrintWriter out = response.getWriter();
			
			if(result>0) {
				out.write("success");
			}else {
				out.write("fail");
			}
			
			out.flush();
			out.close();
			
		}
		

		
		//게시글 수정하기 -- m_no도 같이 넣어줘야됨..
		@GetMapping("updateView")
		public String boardUpdatePage(Model model,@RequestParam("b_no") int b_no , @AuthenticationPrincipal UserImpl userImpl) {
		    
			List<Board> updateViewData = boardService.boardLectureList(b_no,userImpl.getMno());
			if(updateViewData.isEmpty()) {
				System.out.println("강좌없다유------------ 강좌신청 페이지로 이동");
				return "redirect:boardList";
			}else {
				log.info("dd :{}" ,updateViewData);
				//model.addAttribute("userImpl",userImpl);
				model.addAttribute("updateViewData",updateViewData);
				System.out.println("후기남길수있다유");
				return "board/boardUpdate";
			}
	
		}
	/*	
		//?n_no=22
		@PostMapping("update")
		public String noticeUpdate(Notice uptNotice) {
			noticeService.noticeUpdate(uptNotice);
		//	System.out.println("uptNotice = "+uptNotice);
			return "redirect:/notice/detail.do?n_no="+ uptNotice.getN_no();
		}
		
	*/	
	
}
