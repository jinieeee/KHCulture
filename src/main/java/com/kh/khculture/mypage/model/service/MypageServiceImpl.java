package com.kh.khculture.mypage.model.service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.khculture.common.PageInfo;
import com.kh.khculture.lecture.model.vo.LectureOpen;
import com.kh.khculture.mypage.model.dao.MypageMapper;
import com.kh.khculture.payment.model.vo.Payment;
import com.kh.khculture.payment.model.vo.Purchase;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class MypageServiceImpl implements MypageService{

	private MypageMapper mypageMapper;
	
	@Autowired
	public MypageServiceImpl(MypageMapper mypageMapper) {
		this.mypageMapper = mypageMapper;
	}
	
	/* 김현주 */
	@Override
	public List<LectureOpen> getCartList(int mno) {
		/*
		 * Map<String, Object> returnMap = new HashMap<>(); int listCount =
		 * mypageMapper.getcartCount(mno);
		 * 
		 * PageInfo pi = new PageInfo(page, listCount, 10, 10); Map<String, Object> map
		 * = new HashMap<>(); map.put("pi", pi); map.put("mno", mno);
		 */
		
		List<LectureOpen> lecturelist = mypageMapper.getCartList(mno);
		//returnMap.put("pi", pi);
		//returnMap.put("lecturelist", lecturelist);
		return lecturelist;
	}
	
	
	@Transactional
	@Override
	public int putCart(int mno, int lrNo) {
		
		return mypageMapper.putCart(mno, lrNo);
	}
	
	@Transactional
	@Override
	public int deleteCart(Integer lrNo, int mno) {
		return mypageMapper.deleteCart(lrNo, mno);
	}

	@Override
	public int checkCart(int mno, int lrNo) {
		return mypageMapper.checkCart(mno, lrNo);
	}

	@Override
	public Map<String, Object> getPaymentDetails(int mno, int page) {
		Map<String, Object> returnMap = new HashMap<>();
		int listCount = mypageMapper.getPaymentDetailsCount(mno);
		PageInfo pi = new PageInfo(page, listCount, 10, 10);
		
		int startrow = (pi.getPage() - 1)*pi.getBoardLimit() + 1;
		int endrow = startrow + pi.getBoardLimit() - 1;
		
		Map<String, Object> map = new HashMap<>();
		map.put("startrow", startrow);
		map.put("endrow", endrow);
		map.put("mno", mno);
		DecimalFormat formatter = new DecimalFormat("#,###");
		List<Payment> paymentList = mypageMapper.getPaymentDetails(map);
		for(Payment p : paymentList) {
			int total = 0;
			for(Purchase pur : p.getPurchaseList()) {
				if(!pur.getLbStatus().equals("N")) {
					total += Integer.parseInt(pur.getLectureOpen().getLrFee().trim().replace(",", ""));
				}
			}
			p.setTotal(total);
		}
		
		returnMap.put("pi", pi);
		returnMap.put("paymentList", paymentList);
		return returnMap;
	}

	
	
	/* 김현주 */
	
	

}