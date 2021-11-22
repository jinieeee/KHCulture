package com.kh.khculture.mypage.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.khculture.lecture.model.vo.LectureOpen;
import com.kh.khculture.member.model.vo.UserImpl;
import com.kh.khculture.mypage.model.service.MypageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mypage")
public class MypageController {
	private MypageService mypageService;
	
	@Autowired
	public MypageController(MypageService mypageService) {
		this.mypageService = mypageService;
	}
	
	/* 김현주 */
	@GetMapping("cart")
	public void getCartList() {
	}
	
	@GetMapping(value="cart/list", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<LectureOpen> selectCartLists(Model model, HttpServletRequest request, Principal principal){
//		int page = 1;
//		if(request.getParameter("page") != null) {
//			page = Integer.parseInt(request.getParameter("page"));
//		};
		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
		List<LectureOpen> lecturelist = mypageService.getCartList(user.getMno());
		return lecturelist;
	}
	
	@PostMapping(value="cart/put")
	@ResponseBody
	public String putCart(@RequestParam int mno, @RequestParam int lrNo){
		String resultData = "";
		
		int result = mypageService.putCart(mno, lrNo);
		if(result > 0) {
			resultData = "성공";
		} else {
			resultData = "실패";
		}
		return resultData;
	}
	
	@DeleteMapping(value="cart/delete")
	@ResponseBody
	public String deleteCart(@RequestParam int mno, @RequestParam int lrNo){
		String resultData = "";
		
		int result = mypageService.putCart(mno, lrNo);
		if(result > 0) {
			resultData = "성공";
		} else {
			resultData = "실패";
		}
		return resultData;
	}
	
	@GetMapping("paymentDetails")
	public String getPaymentDetails() {
		return "mypage/paymentDetails";
	}
	/* 김현주 */
	
}
