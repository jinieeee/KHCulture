package com.kh.khculture.member.model.vo;

import org.springframework.stereotype.Component;

@Component
public class RandomNum {
	private int randomNum;
	
	public RandomNum() {}

	public int getRandomNum() {
		randomNum = (int)(Math.random()*(999999 - 100000 + 1)) + 100000;
		return randomNum;
	}
}
