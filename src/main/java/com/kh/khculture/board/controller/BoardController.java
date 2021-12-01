package com.kh.khculture.board.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.khculture.board.model.service.BoardService;
import com.kh.khculture.board.model.vo.Board;
import com.kh.khculture.board.model.vo.Lovit;
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
	public String boardList(Model model,@RequestParam(value="page", defaultValue="1") int page, Search search
			, @AuthenticationPrincipal UserImpl userImpl  ) {
		
	//	log.info("ddd : {}",userImpl);
		
		Map<String,Object> returnMap = boardService.boardList(page,search);
		model.addAttribute("search",search);
		model.addAttribute("pi",returnMap.get("pi"));
		model.addAttribute("boardList",returnMap.get("boardList"));
		model.addAttribute("rankList",returnMap.get("rankList"));
		model.addAttribute("principal",userImpl);
		return "board/boardList";
	}
	

/************************************************************** search + 페이징 처리 ********************************
//*********************************************************************************************	*/

	@GetMapping("detail.do")
	public ModelAndView boardDetail( ModelAndView mv, @RequestParam int b_no, @AuthenticationPrincipal UserImpl userImpl) {
		
		int lovit=0;
		Board b = boardService.selectBoard(b_no); //선택한 게시물 
		
		List<Reply> replyList = boardService.selectReplyList(b_no);
		
		if(userImpl != null) {
		lovit =  boardService.selectLovit(b_no, userImpl.getMno()); // 선택한 게시물의 좋아요(로그인한 유저를 구분)
		mv.addObject("Board",b);
		mv.addObject("Lovit", lovit);
		mv.addObject("Reply",replyList);
		mv.addObject("mno", userImpl.getMno());
		mv.setViewName("board/boardDetail");
		}if(userImpl == null) {
			mv.addObject("Board",b);
			mv.setViewName("board/boardDetail");
		}
		
		return mv;
		
	}
	/***************************************************************/
	/***************************************************************/
	/***************************************************************/
	/***************************************************************/
	/************************* 이거 지워지면 처음으로 돌아간**************************************/
/*	
	//로그인 user의 정보가 들어옴
	// /board/insert?user_name=
	@GetMapping("insertView")
	public String boardInsertView(Model model,@AuthenticationPrincipal UserImpl userImpl){
		//로그인한 user의 mNo를 가지고 강좌를 불러옴
		//만약 mNo가 들은 강좌가 없으면 -> alert창으로 들은 강좌가 없다고 안내..
		
		List<LectureOpen> userLectureList = boardService.userLectureList(userImpl.getMno());
		
		if(userLectureList.isEmpty()) {
			System.out.println("강좌없다유------------ 강좌신청 페이지로 이동");
			return "redirect:boardList";
		}else {
			
			model.addAttribute("lecture",userLectureList);
			System.out.println("후기남길수있다유");
			return "board/boardInsert";
		}
		
	}
	
	@PostMapping("insert")
	public String boardInsert(Board NewBoard,@AuthenticationPrincipal UserImpl userImpl) {
		NewBoard.setM_no(userImpl.getMno());
		boardService.boardInsert(NewBoard);
		return "redirect:/board/boardList";
		
	}
	
*/	
	//로그인 유저가 insertView로 가는지, 비로그인 유저가 insertView로 가는지 판단.
	@PostMapping("insertView") 
	@ResponseBody
	public String boardInsertView (@RequestParam("mno") int mno){
		List<LectureOpen> userLectureList = null;
		userLectureList= boardService.userLectureList(mno);
		log.info("mno : {} " , mno);
		log.info("userLectureList:{}",userLectureList);
		return userLectureList.isEmpty()?  "fail" :"success";
	}
	
	//판단후, 로그인한 유저의 mno로 유저의 강좌를 가져옴
	@GetMapping("insert")
	public String boardInsert(Model model, @AuthenticationPrincipal UserImpl userImpl) {
		List<LectureOpen> userLectureList = boardService.userLectureList(userImpl.getMno());
		model.addAttribute("lecture",userLectureList);
		return "board/boardInsert";
		
	}
	
	//작성한 내용을 DB에 insert해줌
	@PostMapping("insert")
	public String boardInsert2(Board NewBoard,@AuthenticationPrincipal UserImpl userImpl,RedirectAttributes rttr) {
		log.info("NewBoard : {}" , NewBoard);
		Integer result = boardService.myRiewSelect(NewBoard.getLr_no(),userImpl.getMno());
		log.info("result : {}", result);
		//강의 no와 유저 no를 조회했을때 결과가 있으면 리다이렉트
		if(result != null) {
			rttr.addFlashAttribute("message","이미 작성된 게시물 입니다.");
			return "redirect:/board/insert";
		}
		
		NewBoard.setM_no(userImpl.getMno());
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
		int result = boardService.boardUpdate(uptBoard);
		
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
	@PostMapping("replyInsert")
	@ResponseBody 
	public List<Reply> replyInsert(@RequestBody Reply reply,Principal principal){
		
		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
		
		Reply r = new Reply(reply.getN_no(),reply.getR_content(),reply.getB_no(),user.getMno(),user.getName());
//		log.info("reply:{}" , r);
		
		int result = boardService.replyInsert(r);
//		log.info("result:{}" , r);
		
		List<Reply> replyList = null; 
		
		if(result > 0) {
			replyList = boardService.selectReplyList(r.getB_no());
//			log.info("replyList : {}" , replyList);
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
			log.info("replyDelete : {}" , replyDelete);
		}
		
		return replyDelete;
	}

}	

