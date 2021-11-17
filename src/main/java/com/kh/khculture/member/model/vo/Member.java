package com.kh.khculture.member.model.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Member {
	private int mno;							// 회원번호
	private String id;							// 회원아이디
	private String pwd;							// 회원비밀번호
	private String name;						// 회원명
	private String phone;						// 연락처
	private String email;						// 이메일
	private String address;						// 주소
	private int hintNo;							// 비밀번호 힌트코드
	private String hintA;						// 비밀번호 힌트답변
	private Date enrollDate;					// 가입일
	private Date modifyDate;					// 회원정보수정일
	private Date pwdChangedDate;				// 비밀번호 변경일
	private Date pwdExpDate;					// 비밀번호 만료일
	private int loginFailedCnt;					// 로그인 실패횟수
	private String accLockYN;					// 계정잠금여부
	private String accSecessionYN;				// 회원탈퇴여부
	private Date accSecessionDate;				// 회원탈퇴일
	private List<MemberRole> memberRoleList;	// 회원권한목록
	
	public Member() {}

}
