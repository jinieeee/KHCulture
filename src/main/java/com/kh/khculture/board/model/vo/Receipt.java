package com.kh.khculture.board.model.vo;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Receipt {
	private int r_no;
	private int m_no;
	private Date payment_date;
	private List<LectureBuy> lectureBuy; 
	
	public Receipt() {}
	
}
