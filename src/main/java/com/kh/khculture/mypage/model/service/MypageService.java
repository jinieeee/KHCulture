package com.kh.khculture.mypage.model.service;

import java.util.List;
import java.util.Map;

import com.kh.khculture.board.model.vo.Board;
import com.kh.khculture.board.model.vo.Search;
import com.kh.khculture.lecture.model.vo.LectureOpen;
import com.kh.khculture.payment.model.vo.Payment;

public interface MypageService {

	/* 김현주 */
	List<LectureOpen> getCartList(int mno);

	int putCart(int mno, int lrNo);

	int deleteCart(Integer lrNo, int mno);

	int checkCart(int mno, int lrNo);
	/* 김현주 */

	Map<String, Object> getPaymentDetails(int mno, int page, String startDate, String endDate);



	/* 정하 */
	List<LectureOpen> mylectureList(int mno);

	Map<String, Object> myReviewList(int page, Search search, int mno);



	
	/* 정하 */


}
