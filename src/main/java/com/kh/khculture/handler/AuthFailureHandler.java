package com.kh.khculture.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.kh.khculture.member.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class AuthFailureHandler implements AuthenticationFailureHandler{

	private String username;
	private MemberService memberService;
	
	@Autowired
	public AuthFailureHandler(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		username = request.getParameter("username");
		// log.info("예외 : {}", exception instanceof BadCredentialsException);
		
		if(exception instanceof BadCredentialsException) {
			// 비밀번호가 일치하지 않을 때 
			// 보안상 아이디, 비밀번호가 일치하지 않는 경우를 분리하지 않음
			memberService.loginFailureCount(username);
			int cnt = memberService.checkFailureCount(username);
			if(cnt >= 5) {
				memberService.lockUserId(username);
			}
			request.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다");
			// request.getSession().setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다");
		} else if(exception instanceof LockedException) {
			// 인증 거부 - 잠긴 계정
			request.setAttribute("msg", "현재 잠긴 계정입니다. 비밀번호 재설정 후 로그인이 가능합니다");
			// request.getSession().setAttribute("msg", "현재 잠긴 계정입니다. 비밀번호 재설정 후 로그인이 가능합니다");
		} else if(exception instanceof CredentialsExpiredException) {
			// 인증 거부 - 비밀번호 유효기간 만료
			request.setAttribute("msg", "비밀번호가 만료된 계정입니다. 비밀번호 재설정 후 로그인이 가능합니다");
			// request.getSession().setAttribute("msg", "비밀번호가 만료된 계정입니다");
		} else if(exception instanceof AuthenticationServiceException) {
			request.setAttribute("msg", "존재하지 않는 계정입니다");
		} else {
			request.setAttribute("msg", "로그인에 실패했습니다. 다시 시도하시기 바랍니다");
		}

		request.getRequestDispatcher("/member/login").forward(request, response);
		// response.sendRedirect("/member/login");
	}

}
