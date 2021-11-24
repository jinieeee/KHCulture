package com.kh.khculture.payment.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
		
		return "payment/lecturedetail";
	}
	
	
	@GetMapping("/payment/procedure")
//	public String postLecturePayment(@RequestParam String[] lrNo) {
	public String postLecturePayment(Model model) {
		
		String[] lrNo = {"111", "115"};
		
		List<Integer> lrNoList = new ArrayList<Integer>();
		
		for(int i=0; i< lrNo.length; i++) {
			
			lrNoList.add(Integer.parseInt(lrNo[i]));
			
		}
		
		List<LectureDetail> lectureList = paymentservice.selectLectureOpenList(lrNoList);
		
		model.addAttribute("lectureList", lectureList);
		
		
		return "payment/procedure";
	}
	
	
	
	
}
