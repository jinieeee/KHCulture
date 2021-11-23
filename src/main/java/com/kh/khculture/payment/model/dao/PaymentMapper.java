package com.kh.khculture.payment.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.khculture.payment.model.vo.LectureDetail;

@Mapper
public interface PaymentMapper {

	LectureDetail selectOneLectureOpen(int lrNo);

}
