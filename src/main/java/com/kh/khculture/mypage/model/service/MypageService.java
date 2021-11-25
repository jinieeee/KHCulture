package com.kh.khculture.mypage.model.service;

import java.util.List;
import java.util.Map;

import com.kh.khculture.lecture.model.vo.LectureOpen;
import com.kh.khculture.payment.model.vo.Payment;

public interface MypageService {

	/* 김현주 */
	List<LectureOpen> getCartList(int mno);

	int putCart(int mno, int lrNo);

	int deleteCart(Integer lrNo, int mno);

	int checkCart(int mno, int lrNo);
	/* 김현주 */

	Map<String, Object> getPaymentDetails(int mno, int page);
}
