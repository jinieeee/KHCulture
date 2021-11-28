package com.kh.khculture.board.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.khculture.board.model.service.BoardService;
import com.kh.khculture.board.model.vo.Board;
import com.kh.khculture.board.model.vo.Lovit;
import com.kh.khculture.board.model.vo.PageInfo;
import com.kh.khculture.board.model.vo.Reply;
import com.kh.khculture.board.model.vo.Search;
import com.kh.khculture.lecture.model.vo.LectureOpen;
import com.kh.khculture.member.model.vo.UserImpl;

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
	public String  boardList(Model model, @RequestParam(value="page", defaultValue="1") int page) {

	int listCount = boardService.getListCount();
	//	System.out.println("총 게시글 개수 : " + listCount);
		
		PageInfo pi = new PageInfo(page, listCount, 10,3);
		List<Board> boardList = boardService.selectList(pi);
	//	System.out.println("후기게시판리스트 = " + boardList);
	
		//랭킹 구현할 board image
		List<Board> rankList = boardService.ranktList();
		
	//	log.info("rankList : {} ",rankList);
		
		model.addAttribute("rankList",rankList);
		model.addAttribute("boardList",boardList);
		model.addAttribute("pi",pi);
	
		return "board/boardList";
	}
/************************************************************** search + 페이징 처리 ********************************
	@GetMapping("search")
	public Map<String, Object> searchBoardList(Model model, @ModelAttribute Search search){
		log.info("search:{}",search); //search:Search(searchCondition=title, searchValue=엄마, title=null, content=null, page=0, startRow=0, endRow=0)
		return boardService.searchBoardList(search); // searchCondition, searchValue
	}
	
//*********************************************************************************************	*/

	@GetMapping("detail.do")
	public ModelAndView noticeDetail( ModelAndView mv, @RequestParam int b_no, @AuthenticationPrincipal UserImpl userImpl) {
	
		
		//System.out.println(n_no);
		Board b = boardService.selectBoard(b_no);
		List<Reply> replyList = b.getReplyList();
		// Mno로만 조회하면 되는데 굳이 b_no를 매개변수에 넣은 이유 : 검색결과
		int lovit =  boardService.selectLovit(b_no, userImpl.getMno()); // mno=01이 눌렀는지 select하고 detail에 뿌려준다.
		

		
		mv.addObject("Board",b);
		mv.addObject("Lovit", lovit);
		mv.addObject("Reply",replyList);
		mv.setViewName("board/boardDetail");
		
		return mv;
		
	}
	
	
	//로그인 user의 정보가 들어옴
	// /board/insert?user_name=
	@GetMapping("insertView")
	public String boardInsertView(Model model,@AuthenticationPrincipal UserImpl userImpl){
		// id의 값이 들어오면... memberMapper로 로그인한 유저인지 확인..?
	//	 log.info("로그인 계정 : {}", member);
	//	log.info("22로그인 계정 : {}",userImpl.getMno());
		
		//로그인한 user의 mNo를 가지고 강좌를 불러옴
		//만약 mNo가 들은 강좌가 없으면 -> alert창으로 들은 강좌가 없다고 안내..
		List<LectureOpen> userLectureList = boardService.userLectureList(userImpl.getMno());
		if(userLectureList.isEmpty()) {
			System.out.println("강좌없다유------------ 강좌신청 페이지로 이동");
			
			return "redirect:boardList";
		}else {
			//log.info("1userLectureList 두그두그 :{}" ,userLectureList.get(0).getLectureBuy().get(0).getLectureOpen().get(0).getLecture().getLThumbnail());
			//log.info("2userLectureList 두그두그 :{}" ,userLectureList.get(1).getLectureBuy().get(0).getLectureOpen().get(0).getLecture().getLThumbnail());
		//	log.info("3userLectureList 두그두그 :{}" ,userLectureList);
			//model.addAttribute("userImpl",userImpl);
			model.addAttribute("lecture",userLectureList);
			System.out.println("후기남길수있다유");
			return "board/boardInsert";
		}
	}
	
	@PostMapping("insert")
	public String boardInsert(Board NewBoard,@AuthenticationPrincipal UserImpl userImpl) {
		NewBoard.setM_no(userImpl.getMno());
	//	log.info("NewBoard : {}",NewBoard);
		boardService.boardInsert(NewBoard);
		return "redirect:/board/boardList";
		
	}
	
	
	
	//게시글 수정하기
	@GetMapping("updateView")
	public String boardUpdatePage(Model model,@RequestParam("bno") int b_no) {
	    // b_no로 그존에 써있는 정보 찾아옴 
		System.out.println("ddd="+b_no);
	//	log.info("b_no:{}", b_no);
		Board b = boardService.updateBoardView(b_no);
	//	log.info("updateView: {}", b);
		model.addAttribute("Board", b);
		return "board/boardUpdate";
		
	}
	
	//?n_no=22
	@PostMapping("update")
	public String boardUpdate(Board uptBoard) {
	//	log.info("uptBoard: {}", uptBoard);
		int result = boardService.boardUpdate(uptBoard);
		
	//	System.out.println("uptNotice = "+uptNotice);
		return "redirect:/board/detail.do?b_no="+ uptBoard.getB_no();
	}
	

	
	@PostMapping("delete")
	public String boardDelete(Board deleteBoard, @RequestParam("b_no") int b_no) {
		boardService.boardDelete(deleteBoard);
		return "redirect:/board/boardList";
	}
	

	//like
	@PostMapping("likeinsert")
	@ResponseBody 
	public String likeinsert(@RequestParam int bno,@AuthenticationPrincipal UserImpl userImpl){
		//bno와 mno를 LOVIT 테이블에 insert해주고, mno가 로그인했을때 좋아요 표시 유지,
		//그렇다면 star은??? - 
	//	log.info("bno : {}",bno); //12
		Lovit lovit = new Lovit(userImpl.getMno(),bno);
		int result = boardService.likeInsert(lovit); //1
	
		System.out.println("등록: "+result);
		
		
		String message = "";
		
		if(result>0) {
			message = "success";
		}else {
			message = "fail";
		}
		
		return message;
		
	}
	
	//like
	@PostMapping("likedelete")
	@ResponseBody 
	public String likedelete(@RequestParam int bno,Principal principal) {
		//bno와 mno를 LOVIT 테이블에 insert해주고, mno가 로그인했을때 좋아요 표시 유지,
		//그렇다면 star은??? - 
	//	log.info("bno : {}",bno); //12
		
		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
		Lovit lovit = new Lovit(user.getMno(),bno);
		int result = boardService.likedelete(lovit); //1
		
		System.out.println("삭제: "+ result);
		
		String message = "";
		
		if(result>0) {
			message = "success";
		}else {
			message = "fail";
		}
		
		return message;
	}
	/*
	@PostMapping("replyInsert")
	public @ResponseBody Map<String, Object> replyInsert(@RequestBody Reply reply) {
		Map<String, Object> map = new HashMap<>();
		 System.out.println("reply : " + reply.getB_no());
		return map;
	}
	*/
	@PostMapping("replyInsert")
	@ResponseBody 
	public List<Reply> replyInsert(@RequestBody Reply reply,Principal principal){
		
		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
		
	//	Reply r = new Reply(reply.getN_no(),reply.getR_content(),reply.getR_enroll_date(),reply.getR_modify_date(),reply.getB_no(),reply.getR_status(),user.getMno(),user.getName());
		Reply r = new Reply(reply.getN_no(),reply.getR_content(),reply.getB_no(),user.getMno(),user.getName());
		log.info("reply:{}" , r);
		
		int result = boardService.replyInsert(r);
		log.info("result:{}" , r);
		
		List<Reply> replyList = null; 
		
		if(result > 0) {
			replyList = boardService.selectReplyList(r.getB_no());
			log.info("replyList : {}" , replyList);
		}
		
		return replyList;
		
	}
	
	@PostMapping("replydelete")
	@ResponseBody
	public List<Reply> replyUpdate(@RequestBody Reply reply){
		int result = boardService.replyDelete(reply.getR_no());
		log.info("result: {}" , result);
		
		
		List<Reply> replyDelete = null;
		
		if(result > 0) {
			replyDelete = boardService.selectReplyList(reply.getB_no());
			log.info("replyList : {}" , replyDelete);
		}
		
		return replyDelete;
	}

}	

