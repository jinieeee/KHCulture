package com.kh.khculture.mypage.model.service;

import java.util.List;
import java.util.Map;

import com.kh.khculture.lecture.model.vo.LectureOpen;

public interface MypageService {

	/* 김현주 */
	List<LectureOpen> getCartList(int mno);
	/* 김현주 */

	int putCart(int mno, int lrNo);
}
