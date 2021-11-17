package com.kh.khculture.member.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.khculture.member.model.vo.Member;
import com.kh.khculture.member.model.vo.MemberRole;
import com.kh.khculture.member.model.vo.PwdHint;

@Mapper
public interface MemberMapper {
	// 회원가입
	int signUpMember(Member member);

	// 비밀번호 힌트 목록 조회
	List<PwdHint> findAllHint();
	
	// 회원권한 삽입
	int insertMemberRole(MemberRole memberRole);

	Member findMemberById(String username);
	
}
