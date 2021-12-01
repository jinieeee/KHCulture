package com.kh.khculture.mypage.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.khculture.board.model.vo.Board;
import com.kh.khculture.board.model.vo.Search;
import com.kh.khculture.common.PageInfo;
import com.kh.khculture.lecture.model.vo.LectureOpen;
import com.kh.khculture.payment.model.vo.Payment;

@Mapper
public interface MypageMapper {
	/* 김현주 */
	List<LectureOpen> getCartList(int mno);
	
	int getcartCount(int mno);

	int putCart(@Param("mno") int mno, @Param("lrNo") int lrNo);

	int deleteCart(@Param("lrNo") Integer lrNo, @Param("mno") int mno);

	int checkCart(@Param("mno") int mno, @Param("lrNo") int lrNo);

	int getPaymentDetailsCount(@Param("mno") int mno, @Param("startDate") String startDate, @Param("endDate") String endDate);

	List<Payment> getPaymentDetails(Map<String, Object> map);
	/* 김현주 */


	/* 정하 */
	List<LectureOpen> mylectureList(int mno);

	//마이후기 개수
//	int getListCount(int mno);
	int getListCount(Map<String, Object> map2);
	//마이후기 리트스
	List<Board> myReviewList(int mno);
	//페이징처리
	List<Board> selectMyreview(Map<String, Object> map);






	
	
	
	
	
	
	
	
	
}
