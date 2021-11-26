package com.kh.khculture.mypage.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.khculture.lecture.model.vo.LectureOpen;
import com.kh.khculture.member.model.vo.UserImpl;
import com.kh.khculture.mypage.model.service.MypageService;
import com.kh.khculture.payment.model.vo.Payment;

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
//	@GetMapping("cart")
//	public void getCartList() {
//	}
	
	@GetMapping("cart")
	public String selectCartLists(Model model, HttpServletRequest request){
//		int page = 1;
//		if(request.getParameter("page") != null) {
//			page = Integer.parseInt(request.getParameter("page"));
//		};
		//UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
		List<LectureOpen> lecturelist = mypageService.getCartList(20);
		model.addAttribute("lecturelist", lecturelist);
		return "mypage/cart";
	}
	
	@PostMapping(value="cart/post")
	@ResponseBody
	public String putCart(@RequestParam int mno, @RequestParam int lrNo){
		
		int check = mypageService.checkCart(mno, lrNo);
		String resultData = "";
		if (check == 0) {
			int result = mypageService.putCart(mno, lrNo);
			if(result > 0) {
				resultData = "성공";
			} else {
				resultData = "실패";
			}
		} else {
			resultData = "보류";
		}
		
		return resultData;
	}
	
	@PutMapping(value="cart/put")
	@ResponseBody
	public String deleteCart(@RequestParam(value="arr[]") List<Integer> arr, Principal principal){
		String resultData = "";
		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
		int result = 0;
		for(Integer lrNo : arr) {
			//log.info("{}", lrNo);
			result += mypageService.deleteCart(lrNo, user.getMno());
		}
		
		if(result == arr.size()) {
			resultData = "성공";
		} else {
			resultData = "실패";
		}
		return resultData;
	}
	
	@GetMapping("paymentDetails")
	public String getPaymentDetails(Model model, @RequestParam(value="page", defaultValue="1") int page, @RequestParam(value="startDate", defaultValue="") String startDate, @RequestParam(value="endDate", defaultValue="") String endDate) {
		//UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
		Map<String, Object> returnMap = mypageService.getPaymentDetails(20, page, startDate, endDate);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("pi", returnMap.get("pi"));
		model.addAttribute("paymentList", returnMap.get("paymentList"));
		return "mypage/paymentDetails";
	}
	
	/* 김현주 */
	
}
