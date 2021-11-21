package com.kh.khculture.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.kh.khculture.handler.AuthEntryPoint;
import com.kh.khculture.handler.AuthFailureHandler;
import com.kh.khculture.handler.AuthSuccessHandler;
import com.kh.khculture.member.model.service.MemberService;

/* 스프링 시큐리티 설정 활성화 + bean 등록 가능 */
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private MemberService memberService;
	private AuthFailureHandler authFailureHandler;
	private AuthSuccessHandler authSuccessHandler;
	private AuthEntryPoint authEntryPoint;
	
	@Autowired
	public SpringSecurityConfiguration(MemberService memberService 
									 , AuthFailureHandler authFailureHandler
									 , AuthSuccessHandler authSuccessHandler
									 , AuthEntryPoint authEntryPoint) {
		this.memberService = memberService;
		this.authFailureHandler = authFailureHandler;
		this.authSuccessHandler = authSuccessHandler;
		this.authEntryPoint = authEntryPoint;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
	}
	
	/* Http 요청에 대한 설정 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.anyRequest().permitAll()
		.and()
			.formLogin()	// 로그인 설정
			.loginPage("/member/login")	// 로그인 페이지 설정
			.successHandler(authSuccessHandler) // 로그인 성공 시 핸들러
			// .successForwardUrl("/")	// 로그인 성공 시 랜딩 페이지 설정
			.failureHandler(authFailureHandler) // 로그인 실패 시 핸들러
		.and()
			.logout()	// 로그아웃 설정
			.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")) // 로그아웃 요청 주소
			.deleteCookies("JSESSIONID")	// JSESSIONID 쿠키 삭제
			.invalidateHttpSession(true)	// 세션 만료

			.logoutSuccessUrl("/")			// 로그아웃 성공 시 랜딩 페이지
		.and()
			.exceptionHandling()
			.authenticationEntryPoint(authEntryPoint)
			.accessDeniedPage("/common/denied");

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
	}
	
	
}
