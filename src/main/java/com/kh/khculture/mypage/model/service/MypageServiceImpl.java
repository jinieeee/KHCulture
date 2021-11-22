package com.kh.khculture.mypage.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.khculture.lecture.model.vo.LectureOpen;
import com.kh.khculture.mypage.model.dao.MypageMapper;

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
	/* 김현주 */
	
	@Transactional
	@Override
	public int putCart(int mno, int lrNo) {
		
		return mypageMapper.putCart(mno, lrNo);
	}

}
