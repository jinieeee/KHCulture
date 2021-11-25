package com.kh.khculture.payment.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.khculture.member.model.vo.UserImpl;
import com.kh.khculture.payment.model.service.PaymentService;
import com.kh.khculture.payment.model.vo.LectureDetail;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PaymentController {
	
	private PaymentService paymentService;
	
	private IamportClient api;
	
	@Autowired
	public PaymentController(PaymentService paymentService) {
		
		this.paymentService= paymentService;
		this.api = new IamportClient("3243553387779053","cf072b8cf7310bfcce970e2439ca76ddd55a3f6a5012749e6c43e12633badc7ae160268459ac7ed5");
		
	}
	
	
	@GetMapping("/lecture/detail/{lrNo}")
	public String lectureDetail(Model model, @PathVariable int lrNo) {
		
		
		LectureDetail lectureDetail = paymentService.selectOneLectureOpen(lrNo);
		
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
	public String postLecturePayment(Model model, Principal principal) {
		
		
		if(principal != null) {
			UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
			
			
			model.addAttribute("mno", user.getMno());
			model.addAttribute("name", user.getName());
			model.addAttribute("email", user.getEmail());
		}
		
		
		
		String[] lrNo = {"3"};		
		
		List<Integer> lrNoList = new ArrayList<Integer>();
		
		for(int i=0; i< lrNo.length; i++) {
			
			lrNoList.add(Integer.parseInt(lrNo[i]));
			
		}
		
		List<LectureDetail> lectureList = paymentService.selectLectureOpenList(lrNoList);
		
		model.addAttribute("lectureList", lectureList);
		
		
		return "payment/procedure";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/verification/{imp_uid}")
	public IamportResponse<Payment> paymentByImpUid(
			Model model
			, Locale locale
			, HttpSession session
			, BigDecimal paidAmount
			, int mno
			, @RequestParam(value="lrNo[]") String[] lrNo
			, @PathVariable(value= "imp_uid") String imp_uid) throws IamportResponseException, IOException{
		
		
		
		int lrNoArr[] = new int[lrNo.length];
		
		for(int i=0; i<lrNo.length; i++) {
			
			lrNoArr[i] = Integer.parseInt(lrNo[i]);
		}
		
		if(paidAmount.equals(api.paymentByImpUid(imp_uid).getResponse().getAmount())) {
			
			String merchantUid = api.paymentByImpUid(imp_uid).getResponse().getMerchantUid();
			
			log.info("{}", merchantUid.substring(5));
			
			int rNo = Integer.parseInt(merchantUid.substring(5));
			
			int result1 = paymentService.insertReceipt(rNo, mno);
			int result2 = 0;
			int result3 = 0;
			
			for(int lr : lrNoArr) {
				
				result2 += paymentService.insertLectureBuy(rNo, lr);
				result3 += paymentService.updateLrCount(lr);
			}
			
			
			if(result1 == 1 && result2 == lrNoArr.length && result3 == lrNoArr.length) {
				
				// 성공
				log.info("성공입니다.");
				return api.paymentByImpUid(imp_uid);
				
			} else {
				
				// 실패
				log.info("실패입니다.");
				return api.paymentByImpUid("");
			}
			
		}
		
		return api.paymentByImpUid("");
		
			
	}
	
	@PutMapping(value="/payment/cancel")
	@ResponseBody
	public String Cancel(@RequestParam(value="arr[]") List<Integer> arr, Principal principal, @RequestParam int rNo){
		
		int result1 = 0;
		int result2 = 0;
		int result1Old = 0;
		for(int lrNo : arr) {
			
			result1 += paymentService.cancelLectureBuy(rNo, lrNo);
			if(result1 == result1Old) {return "실패";}
			
			result2 += paymentService.updateDecreaseLrCount(lrNo);
			
			result1Old = result1;
		}
		
		if(result1 == arr.size() && result2 ==arr.size()) {
			
			return "성공";
			
		}
		
		return "실패";
	}
	
	
	
}
