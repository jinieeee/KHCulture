package com.kh.khculture.mypage.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.khculture.lecture.model.vo.LectureOpen;

@Mapper
public interface MypageMapper {
	/* 김현주 */
	List<LectureOpen> getCartList(int mno);
	/* 김현주 */

	int getcartCount(int mno);
//	int putCart(int mno, int lrNo);

	int putCart(@Param("mno") int mno, @Param("lrNo") int lrNo);
}
