package com.kh.khculture.board.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		//log.info("rankList : {}" , returnMap.get("rankList"));
		return "board/boardList";
	}
	

	@GetMapping("detail.do")
	public ModelAndView boardDetail( ModelAndView mv, @RequestParam int b_no, @AuthenticationPrincipal UserImpl userImpl
			                             ,HttpServletRequest request, HttpServletResponse response) {
		
		int lovit=0;
		//?????????????????? ?????? ?????? ?????????
		Cookie[] cookies = request.getCookies();	
		String bcount = "";
		if(cookies != null && cookies.length > 0) {
			for(Cookie c: cookies) {
				if(c.getName().equals("bcount")) {
					bcount = c.getValue();
				}
			}
		}
		
		if(bcount.indexOf("|"+b_no +"|") == -1) {
			Cookie newBcount  = new Cookie("bcount",bcount + "|" +b_no+ "|" );
			/* newBcount.setMaxAge(60*60*24); */
			newBcount.setMaxAge(60*60);
			response.addCookie(newBcount);
			int result = boardService.increaseCount(b_no);
			
			if(result > 0) {
				System.out.println("????????? ?????? ??????");
			}else {
				System.out.println("????????? ?????? ??????");
			}
		}
		
		Board b = boardService.selectBoard(b_no); //????????? ????????? 
		
		List<Reply> replyList = boardService.selectReplyList(b_no);
		
		if(userImpl != null) {
		lovit =  boardService.selectLovit(b_no, userImpl.getMno()); // ????????? ???????????? ?????????(???????????? ????????? ??????)
		mv.addObject("Board",b);
		mv.addObject("Lovit", lovit);
		mv.addObject("Reply",replyList);
		mv.addObject("mno", userImpl.getMno());
		mv.setViewName("board/boardDetail");
		}if(userImpl == null) {
			mv.addObject("Board",b);
			mv.setViewName("board/boardDetail");
		}
		
		log.info("Board : {}" , b);
		return mv;
		
	}
	/* ********************************************* */

	//????????? ????????? insertView??? ?????????, ???????????? ????????? insertView??? ????????? ??????.
	@PostMapping("insertView") 
	@ResponseBody
	public String boardInsertView (@RequestParam("mno") int mno){
		List<LectureOpen> userLectureList = null;
		userLectureList= boardService.userLectureList(mno);
		return userLectureList.isEmpty()?  "fail" :"success";
	}
	
	//?????????, ???????????? ????????? mno??? ????????? ????????? ?????????
	@GetMapping("insert")
	public String boardInsert(Model model, @AuthenticationPrincipal UserImpl userImpl) {
		List<LectureOpen> userLectureList = boardService.userLectureList(userImpl.getMno());
		model.addAttribute("lecture",userLectureList);
		return "board/boardInsert";
		
	}
	
	//????????? ????????? DB??? insert??????
	@PostMapping("insert")
	public String boardInsert2(Board NewBoard,@AuthenticationPrincipal UserImpl userImpl,RedirectAttributes rttr) {
		
		Integer result = boardService.myRiewSelect(NewBoard.getLr_no(),userImpl.getMno());
		if(result != null) {
			rttr.addFlashAttribute("message","?????? ????????? ????????? ?????????.");
			return "redirect:/board/insert";
		}
		NewBoard.setM_no(userImpl.getMno());
		boardService.boardInsert(NewBoard);
		return "redirect:/board/boardList";
	}
	
	
	//????????? ????????????
	@GetMapping("updateView")
	public String boardUpdatePage(Model model,@RequestParam("bno") int b_no) {
		System.out.println("ddd="+b_no);
		Board b = boardService.updateBoardView(b_no);
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
	public String boardDelete(Board deleteBoard, @RequestParam("b_no") int b_no,RedirectAttributes rttr) {
		boardService.boardDelete(deleteBoard);
		log.info("deleteBoard:{}",deleteBoard);
		rttr.addFlashAttribute("boardDelete","????????? ["+deleteBoard.getB_title() + "]??? ???????????? ?????? ???????????????.");
		return "redirect:/board/boardList";
	}
	

	//like
	@PostMapping("likeinsert")
	@ResponseBody 
	public String likeinsert(@RequestParam int bno,@AuthenticationPrincipal UserImpl userImpl){
		Lovit lovit = new Lovit(userImpl.getMno(),bno);
		int result = boardService.likeInsert(lovit); //1
	
		System.out.println("??????: "+result);
		
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
		
		System.out.println("??????: "+ result);
		
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
		
		int result = boardService.replyInsert(r);
		
		List<Reply> replyList = null; 
		
		if(result > 0) {
			//????????? ????????????
			replyList = boardService.selectReplyList(r.getB_no());
		}
		return replyList;
	}
	
	@PostMapping("replydelete")
	@ResponseBody
	public List<Reply> replyUpdate(@RequestBody Reply reply){
		int result = boardService.replyDelete(reply.getR_no());
		
		List<Reply> replyDelete = null;
		
		if(result > 0) {
			replyDelete = boardService.selectReplyList(reply.getB_no());
			log.info("replyDelete : {}" , replyDelete);
		}
		
		return replyDelete;
	}
	@PostMapping("replymodify")
	@ResponseBody
	public int replyModify(@RequestBody Reply reply) {
		int result = boardService.replyModify(reply);
		return result;
	}

}	

