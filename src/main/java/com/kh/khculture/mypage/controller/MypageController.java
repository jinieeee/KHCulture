package com.kh.khculture.mypage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.khculture.mypage.service.MypageService;

@Controller
@RequestMapping("/mypage/")
public class MypageController {
	private MypageService mypageService;
	
	@Autowired
	public MypageController(MypageService mypageService) {
		this.mypageService = mypageService;
	}
	
	@GetMapping("cart")
	public String getCartList() {
		return "mypage/cart";
	}
	
	@GetMapping("paymentDetails")
	public String getPaymentDetails() {
		return "mypage/paymentDetails";
	}
	
}
