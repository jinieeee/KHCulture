package com.kh.khculture.payment.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.khculture.payment.model.dao.PaymentMapper;
import com.kh.khculture.payment.model.vo.LectureDetail;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService{
	
	private PaymentMapper paymentMapper;
	
	@Autowired
	public PaymentServiceImpl(PaymentMapper paymentMapper) {
		
		this.paymentMapper = paymentMapper;
	}
	
	@Override
	public LectureDetail selectOneLectureOpen(int lrNo) {
		
		return paymentMapper.selectOneLectureOpen(lrNo);
	}
	
	

}
