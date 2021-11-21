package com.kh.khculture.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.kh.khculture.member.model.service.MemberService;

@Configuration
public class AuthSuccessHandler implements AuthenticationSuccessHandler{
	
	private String username;
	private MemberService memberService;
	
	@Autowired
	public AuthSuccessHandler(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		username = request.getParameter("username");
		System.out.println(username);
		
		memberService.initializeFailureCount(username);
		request.getRequestDispatcher("/").forward(request, response);
	}

}
