package com.kh.khculture.payment.model.service;

import java.util.List;

import com.kh.khculture.payment.model.vo.LectureDetail;

public interface PaymentService {

	LectureDetail selectOneLectureOpen(int lrNo);

	List<LectureDetail> selectLectureOpenList(List<Integer> lrNoList);

	int insertReceipt(int rNo, int mNo);

	int insertLectureBuy(int rNo, int lr);

	int updateLrCount(int lr);

	int cancelLectureBuy(int rNo, int lrNo);

	int updateDecreaseLrCount(int lrNo);



}
