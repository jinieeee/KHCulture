package com.kh.khculture.payment.model.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Payment {
	private int rNo; //결제내역 번호
	private Date paymentDate; //결제일
	private int mNo; //회원번호
	private int total; //총 결제 수강료
	private List<Purchase> purchaseList = new ArrayList<>();
	
}
