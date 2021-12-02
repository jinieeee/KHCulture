package com.kh.khculture.mypage.model.service;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.khculture.board.model.vo.Board;
import com.kh.khculture.board.model.vo.Search;
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
		for(LectureOpen l : lecturelist) {
			Calendar cal = new GregorianCalendar();
			cal.setTime(l.getLrStartDate());
			cal.add(Calendar.DATE, -30);
			l.setLrOpenDate(cal.getTime());
		}
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
	public Map<String, Object> getPaymentDetails(int mno, int page, String startDate, String endDate) {
		Map<String, Object> returnMap = new HashMap<>();
		int listCount = mypageMapper.getPaymentDetailsCount(mno, startDate, endDate);
		PageInfo pi = new PageInfo(page, listCount, 10, 10);
		
		int startrow = (pi.getPage() - 1)*pi.getBoardLimit() + 1;
		int endrow = startrow + pi.getBoardLimit() - 1;
		
		Map<String, Object> map = new HashMap<>();
		map.put("startrow", startrow);
		map.put("endrow", endrow);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
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
	
	/* 정하 */
	
	/* 정하 */
	@Override
	public List<LectureOpen> mylectureList(int mno) {
		return mypageMapper.mylectureList(mno);
	}


	@Override
	public Map<String, Object> myReviewList(int page, Search search, int mno) {
		
		Map<String,Object> returnMap = new HashMap<>();
		
			Map<String,Object> map2 = new HashMap<>();
			map2.put("SearchCondition",search.getSearchCondition());
			map2.put("SearchValue", search.getSearchValue());
			map2.put("mno",mno);
			
			int listCount = mypageMapper.getListCount(map2);
	        log.info("listCount : {}" , listCount);		
			PageInfo pi = new PageInfo(page, listCount, 10, 10);
			int startRow = (pi.getPage()-1)*pi.getBoardLimit()+1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			
			log.info("pi : {}",startRow);
			
			Map<String, Object> map = new HashMap<>();
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("SearchCondition",search.getSearchCondition());
			map.put("SearchValue", search.getSearchValue());
			map.put("mno" , mno);
		
		List<Board> myReviewList = mypageMapper.selectMyreview(map);
		
		log.info("myReviewList : {}",myReviewList);
		
		returnMap.put("pi",pi);
		returnMap.put("myReviewList", myReviewList);
		return returnMap;
	}

	
	

	
	
}
