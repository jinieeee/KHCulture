package com.kh.khculture.member.model.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.khculture.member.model.dao.MemberMapper;
import com.kh.khculture.member.model.vo.Authority;
import com.kh.khculture.member.model.vo.Member;
import com.kh.khculture.member.model.vo.MemberRole;
import com.kh.khculture.member.model.vo.PwdHint;
import com.kh.khculture.member.model.vo.UserImpl;

import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{

	private MemberMapper memberMapper;
	
	@Autowired
	public MemberServiceImpl(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	
	// 회원가입
	@Transactional
	@Override
	public int signUpMember(Member member) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		// 비밀번호 암호화 처리
		member.setPwd(passwordEncoder.encode(member.getPwd()));
		int result1 = memberMapper.signUpMember(member);
		
		// 회원권한 처리
		MemberRole memberRole = new MemberRole();
		memberRole.setAuthorityCode(1);
		int result2 = memberMapper.insertMemberRole(memberRole);
		
		return (result1 > 0 && result2 > 0) ? 1 : 0; 
	}
	
	// 문자 전송
	@Override
	public void sendAuthCode(String phone, int randomNumber) {
		String api_key = "NCSPIKZRNTHOCRMG"; // API key
		String api_secret = "RCDJ9MZ98P0W4WNWGTKNLD68FIBPROKB"; // API secret
		Message coolsms = new Message(api_key, api_secret);
		
		HashMap<String, String> params = new HashMap<>();
		params.put("to", phone);			// 수신인
		params.put("from", "01040660340");	// 발신인
		params.put("type", "SMS");
		params.put("text", "[KH문화센터] 인증번호는 " + randomNumber + " 입니다");	// 문자 내용
		
		try {
			JSONObject obj = (JSONObject)coolsms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<PwdHint> findAllHint() {
		return memberMapper.findAllHint();
	}
	
	// 로그인
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberMapper.findMemberById(username);

		if(member == null) {
			member = new Member();
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		if(member.getMemberRoleList() != null) {
			List<MemberRole> roleList = member.getMemberRoleList();
			
			for(MemberRole role : roleList) {
				Authority authority = role.getAuthority();
				
				if(authority != null) {
					authorities.add(new SimpleGrantedAuthority(authority.getAuthorityName()));
				}
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("YY-MM-dd");
		boolean enabled = member.getAccSecessionYN().equals("N")? true: false;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = sdf.format(member.getPwdExpDate()).compareTo(sdf.format(new Date())) >= 0? true: false;
		boolean accountNonLocked = member.getAccLockYN().equals("N")? true: false;
		// log.info("로그인 계정 : {}", member);
		// log.info("사용 가능 계정 여부 : {}", enabled);
		// log.info("계정 만료 여부 : {}", accountNonExpired);
		// log.info("비밀번호 만료 여부 : {}", credentialsNonExpired);
		// log.info("비밀번호 만료일 : {}", sdf.format(member.getPwdExpDate()));
		// log.info("현재일 : {}", sdf.format(new Date()));
		// log.info("비밀번호 만료 여부 : {}", sdf.format(member.getPwdExpDate()).compareTo(sdf.format(new Date())));
		// log.info("계정 잠금 여부 : {}", accountNonLocked);
		UserImpl user = 
				new UserImpl(member.getId(), member.getPwd(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		user.setDetails(member);
		return user;
	}

	// 중복아이디 조회
	@Override
	public int checkId(String userId) {
		List<Member> memberList = memberMapper.checkId(userId);
		return (memberList.size() > 0) ? 1 : 0;
	}
	
	// 로그인 실패 횟수 카운트
	@Override
	public void loginFailureCount(String userId) {
		memberMapper.loginFailureCount(userId);
	}
	
	// 로그인 실패 횟수 조회
	@Override
	public int checkFailureCount(String userId) {
		return memberMapper.checkFailureCount(userId);
	}
	
	// 계정 잠금
	@Override
	public void lockUserId(String userId) {
		memberMapper.lockUserId(userId);
		
	}
	
	// 로그인 실패 횟수 초기화
	@Override
	public void initializeFailureCount(String userId) {
		memberMapper.initializeFailureCount(userId);
	}

	// 비밀번호 재설정용 계정 조회
	@Override
	public String findPwd(Member member) {
		Member findMember = memberMapper.findPwd(member);
		if(findMember == null) {
			findMember = new Member();
		}
		
		return findMember.getId();
	}
	
	// 비밀번호 재설정
	@Override
	public int resetPwd(Member member) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setPwd(passwordEncoder.encode(member.getPwd()));
		
		return memberMapper.resetPwd(member);
	}

	// 아이디 찾기용 계정 조회
	@Override
	public Member findId(Member member) {
		Member findMember = memberMapper.findId(member);
		if(findMember == null) {
			findMember = new Member();
		}
		return findMember;
	}
}
