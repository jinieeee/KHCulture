package com.kh.khculture.payment.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
