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
	
	// 로그인 실패 횟수 카운트
	void loginFailureCount(String userId);

	// 로그인 실패 횟수 조회
	int checkFailureCount(String userId);

	// 계정 잠금
	void lockUserId(String userId);

	// 로그인 실패 횟수 초기화
	void initializeFailureCount(String userId);

	// 비밀번호 재설정용 계정 조회
	String findPwd(Member member);
	
	// 비밀번호 재설정
	int resetPwd(Member member);

	// 아이디 찾기용 계정 조회
	Member findId(Member member);
}
