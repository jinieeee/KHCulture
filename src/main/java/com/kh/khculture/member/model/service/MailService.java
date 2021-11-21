package com.kh.khculture.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.kh.khculture.member.model.vo.Mail;

@Service
public class MailService {
	@Autowired
	private JavaMailSender mailSender;
	
	private static final String FROM_ADDRESS = "khculture4@gmail.com";
	
	public void sendEmailAuth(Mail mail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(FROM_ADDRESS);
		message.setTo(mail.getAddress());
		message.setSubject(mail.getTitle());
		message.setText(mail.getMessage());
		
		mailSender.send(message);
	}

}
