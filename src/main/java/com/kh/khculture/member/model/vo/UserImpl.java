package com.kh.khculture.member.model.vo;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserImpl extends User {

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
	
//	public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//		super(username, password, authorities);
//	}


	public UserImpl(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
	
	public void setDetails(Member member) {
		this.mno = member.getMno();
		this.id = member.getId();
		this.pwd = member.getPwd();
		this.name = member.getName();
		this.phone = member.getPhone();
		this.email = member.getEmail();
		this.address = member.getAddress();
		this.hintNo = member.getHintNo();
		this.hintA = member.getHintA();
		this.enrollDate = member.getEnrollDate();
		this.modifyDate = member.getModifyDate();
		this.pwdChangedDate = member.getPwdChangedDate();
		this.pwdExpDate = member.getPwdExpDate();
		this.loginFailedCnt = member.getLoginFailedCnt();
		this.accLockYN = member.getAccLockYN();
		this.accSecessionYN = member.getAccSecessionYN();
		this.accSecessionDate = member.getAccSecessionDate();
		this.memberRoleList = member.getMemberRoleList();
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getHintNo() {
		return hintNo;
	}

	public void setHintNo(int hintNo) {
		this.hintNo = hintNo;
	}

	public String getHintA() {
		return hintA;
	}

	public void setHintA(String hintA) {
		this.hintA = hintA;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Date getPwdChangedDate() {
		return pwdChangedDate;
	}

	public void setPwdChangedDate(Date pwdChangedDate) {
		this.pwdChangedDate = pwdChangedDate;
	}

	public Date getPwdExpDate() {
		return pwdExpDate;
	}

	public void setPwdExpDate(Date pwdExpDate) {
		this.pwdExpDate = pwdExpDate;
	}

	public int getLoginFailedCnt() {
		return loginFailedCnt;
	}

	public void setLoginFailedCnt(int loginFailedCnt) {
		this.loginFailedCnt = loginFailedCnt;
	}

	public String getAccLockYN() {
		return accLockYN;
	}

	public void setAccLockYN(String accLockYN) {
		this.accLockYN = accLockYN;
	}

	public String getAccSecessionYN() {
		return accSecessionYN;
	}

	public void setAccSecessionYN(String accSecessionYN) {
		this.accSecessionYN = accSecessionYN;
	}

	public Date getAccSecessionDate() {
		return accSecessionDate;
	}

	public void setAccSecessionDate(Date accSecessionDate) {
		this.accSecessionDate = accSecessionDate;
	}

	public List<MemberRole> getMemberRoleList() {
		return memberRoleList;
	}

	public void setMemberRoleList(List<MemberRole> memberRoleList) {
		this.memberRoleList = memberRoleList;
	}

	@Override
	public String toString() {
		return "UserImpl [mno=" + mno + ", id=" + id + ", pwd=" + pwd + ", name=" + name + ", phone=" + phone
				+ ", email=" + email + ", address=" + address + ", hintNo=" + hintNo + ", hintA=" + hintA
				+ ", enrollDate=" + enrollDate + ", modifyDate=" + modifyDate + ", pwdChangedDate=" + pwdChangedDate
				+ ", pwdExpDate=" + pwdExpDate + ", loginFailedCnt=" + loginFailedCnt + ", accLockYN=" + accLockYN
				+ ", accSecessionYN=" + accSecessionYN + ", accSecessionDate=" + accSecessionDate + ", memberRoleList="
				+ memberRoleList + "]";
	}
}
