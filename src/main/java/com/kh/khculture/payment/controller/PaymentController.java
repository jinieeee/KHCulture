package com.kh.khculture.payment.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.khculture.member.model.vo.UserImpl;
import com.kh.khculture.payment.model.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/payment")
public class PaymentController {
	private PaymentService paymentservice;
	
	@Autowired
	public PaymentController(PaymentService paymentservice) {
		this.paymentservice= paymentservice;
	}
	
	@GetMapping("approval")
	public String postLecturePayment(@RequestParam String[] lrNo) {
		log.info("{}", Arrays.toString(lrNo));
		return "";
	}
	
	@PutMapping(value="cancel")
	@ResponseBody
	public String Cancel(@RequestParam(value="arr[]") List<Integer> arr, Principal principal){
		String resultData = "";
		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
		log.info("{}", arr); //강좌오픈번호
		log.info("{}", user.getMno()); //유저번호
//		int result = 0;
//		for(Integer lrNo : arr) {
//			//log.info("{}", lrNo);
//			result += mypageService.deleteCart(lrNo, user.getMno());
//		}
//		
//		if(result == arr.size()) {
//			resultData = "성공";
//		} else {
//			resultData = "실패";
//		}
		return "성공";
	}
}
