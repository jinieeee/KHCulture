package com.kh.khculture.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.khculture.member.model.service.MailService;
import com.kh.khculture.member.model.vo.Mail;
import com.kh.khculture.member.model.vo.RandomNum;

@Controller
public class MailController {
	private MailService mailService;
	private RandomNum randomNum;
	private Mail mail;
	
	public MailController(MailService mailService, RandomNum randomNum, Mail mail) {
		this.mailService = mailService;
		this.randomNum = randomNum;
		this.mail = mail;
	}
	
	// 이메일 인증
	@PostMapping(value="/sendEmailAuth")
	@ResponseBody
	public String sendEmailAuth(@RequestParam String email) {
		// log.info("{}", email);
		int authCode = randomNum.getRandomNum();
		// 이메일 전송
		mail.setAddress(email);
		mail.setTitle("KH문화센터 아이디 찾기 인증 메일입니다");
		mail.setMessage("인증번호 : " + authCode);
		mailService.sendEmailAuth(mail);
		return Integer.toString(authCode);
	}
}
