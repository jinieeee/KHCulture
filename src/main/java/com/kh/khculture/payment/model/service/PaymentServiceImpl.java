package com.kh.khculture.payment.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.khculture.payment.model.dao.PaymentMapper;
import com.kh.khculture.payment.model.vo.LectureDetail;


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


	@Override
	public List<LectureDetail> selectLectureOpenList(List<Integer> lrNoList) {
		
		return paymentMapper.selectLectureOpenList(lrNoList);
	}
	
	

}
