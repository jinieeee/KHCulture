package com.kh.khculture.payment.controller;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.khculture.payment.model.service.PaymentService;
import com.kh.khculture.payment.model.vo.LectureDetail;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PaymentController {
	
	private PaymentService paymentservice;
	
	@Autowired
	public PaymentController(PaymentService paymentservice) {
		this.paymentservice= paymentservice;
	}
	
	
	@GetMapping("/lecture/detail/{lrNo}")
	public String lectureDetail(Model model, @PathVariable int lrNo) {
		
		
		LectureDetail lectureDetail = paymentservice.selectOneLectureOpen(lrNo);
		
		Date receiptDate = new Date(lectureDetail.getLrStartDate().getYear(),
				lectureDetail.getLrStartDate().getMonth()-1, lectureDetail.getLrStartDate().getDate());
		
		Date today = new Date();
		
		model.addAttribute("lectureDetail", lectureDetail);
		model.addAttribute("receiptDate", receiptDate);
		model.addAttribute("today", today);
		
		return "management/lecturedetail";
	}
	
	
	@GetMapping("/payment/approval")
	public String postLecturePayment(@RequestParam String[] lrNo) {
		log.info("{}", Arrays.toString(lrNo));
		return "";
	}
	
	
	
	
}
