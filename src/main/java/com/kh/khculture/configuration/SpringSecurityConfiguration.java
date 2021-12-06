package com.kh.khculture.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
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
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
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
				// 관리자페이지 전체 접근권한
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/admin/memberList/deleteAcc").hasRole("HEAD")
				.antMatchers(HttpMethod.POST, "/admin/memberList/roleUpdate").hasRole("HEAD")
				// 마이페이지 전체 접근권한
				.antMatchers("/mypage/**").hasRole("MEMBER")
				// 강좌등록 & 개설 & 결제 접근권한 (장영재)
				.antMatchers("/lecturelist").hasRole("ADMIN")
				.antMatchers("/lectureregist").hasRole("ADMIN")
				.antMatchers("/registserver").hasRole("ADMIN")
				.antMatchers("/lectureupdate/**").hasRole("ADMIN")
				.antMatchers("/updateserver").hasRole("ADMIN")
				.antMatchers("/lecturedelete/**").hasRole("ADMIN")
				.antMatchers("/openlist").hasRole("ADMIN")
				.antMatchers("/openregist").hasRole("ADMIN")
				.antMatchers("/openserver").hasRole("ADMIN")
				.antMatchers("/openupdate/**").hasRole("ADMIN")
				.antMatchers("/updateOpenserver").hasRole("ADMIN")
				.antMatchers("/opendelete/**").hasRole("ADMIN")
				.antMatchers("/payment/**").hasRole("MEMBER")
				.antMatchers("/verification/**").hasRole("MEMBER")
				// 공지사항 & 게시판 접근권한 (양정하)
				.antMatchers("/notice/updateView/**").hasRole("ADMIN")
				.antMatchers("/notice/insert").hasRole("ADMIN")
				.antMatchers("/notice/delete").hasRole("ADMIN")
				.antMatchers("/board/insert").hasRole("MEMBER")
				.antMatchers("/board/delete").hasRole("MEMBER")
				.antMatchers("/board/updateView").hasRole("MEMBER")
				// 1:1문의 & 강사등록 (황재윤)
				.antMatchers("/questionnaire/insert").hasRole("MEMBER")
				.antMatchers("/questionnaire/detail.do?").hasRole("MEMBER")
				.anyRequest().permitAll()
		.and()
			.formLogin()	// 로그인 설정
			.loginPage("/member/login")	// 로그인 페이지 설정
			//.successForwardUrl("/")	// 로그인 성공 시 랜딩 페이지 설정
			.successHandler(authSuccessHandler) // 로그인 성공 시 핸들러
			.failureHandler(authFailureHandler) // 로그인 실패 시 핸들러
		.and()
			.logout()	// 로그아웃 설정
			.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")) // 로그아웃 요청 주소
			.deleteCookies("JSESSIONID")	// JSESSIONID 쿠키 삭제
			.invalidateHttpSession(true)	// 세션 만료

			.logoutSuccessUrl("/")			// 로그아웃 성공 시 랜딩 페이지
		.and()
			.exceptionHandling()
			.accessDeniedPage("/common/denied")
			.authenticationEntryPoint(authEntryPoint);

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
	}
	
	
}
