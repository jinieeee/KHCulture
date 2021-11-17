package com.kh.khculture.member.model.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.kh.khculture.member.model.vo.Member;
import com.kh.khculture.member.model.vo.PwdHint;

public interface MemberService extends UserDetailsService{
	
	// 회원가입
	int signUpMember(Member member);
	
	// 전화번호 인증
	void sendAuthCode(String phone, int randomNumber);
	
	// 비밀번호 힌트 목록 조회
	List<PwdHint> findAllHint();
	
	// 중복아이디 조회
	int checkId(String userId);
}
