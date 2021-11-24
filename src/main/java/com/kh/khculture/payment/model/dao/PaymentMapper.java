package com.kh.khculture.payment.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.khculture.payment.model.vo.LectureDetail;

@Mapper
public interface PaymentMapper {

	LectureDetail selectOneLectureOpen(int lrNo);

	List<LectureDetail> selectLectureOpenList(List<Integer> lrNoList);

}
