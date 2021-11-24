package com.kh.khculture.payment.model.service;

import java.util.List;

import com.kh.khculture.payment.model.vo.LectureDetail;

public interface PaymentService {

	LectureDetail selectOneLectureOpen(int lrNo);

	List<LectureDetail> selectLectureOpenList(List<Integer> lrNoList);



}
