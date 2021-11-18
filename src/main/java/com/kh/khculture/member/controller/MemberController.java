package com.kh.khculture.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.khculture.member.model.service.MemberService;
import com.kh.khculture.member.model.vo.Member;
import com.kh.khculture.member.model.vo.PwdHint;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {
	
	private MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("login")
	public void login() {}
	
	@GetMapping("signUpMember")
	public void signUpMember() {}
	
	@GetMapping("findId")
	public void findId() {}
	
	@GetMapping("findPwd")
	public void findPwd() {}
	
	@PostMapping("signUpMember")
	public String signUpMember(Member member, RedirectAttributes rttr/*, HttpSession session */) {
		// log.info("회원 정보 조회 : {}", member.getAddress());
		String msg = memberService.signUpMember(member) > 0 ? "회원가입 완료, 로그인하실 수 있습니다": "회원가입 실패";
		rttr.addFlashAttribute("msg", msg);
		return "redirect:/member/login";
	}
	
	// 힌트 리스트 조회
	@GetMapping(value="pwdHint", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<PwdHint> findHintList(){
		return memberService.findAllHint();
	}
	
	// 전화번호 인증
	@GetMapping(value="sendAuth")
	@ResponseBody
	public String sendAuth(@RequestParam("phone") String phone) {
		// System.out.println(phone);
		// 6자리 난수 생성
		int randomNumber = (int)(Math.random()*(999999 - 100000 + 1)) + 100000;
		
		// memberService.sendAuthCode(phone, randomNumber);
		return Integer.toString(randomNumber);
	}

	@PostMapping("checkId")
	@ResponseBody
	public String checkId(String userId) {
		
		String result = (memberService.checkId(userId) == 0) ? "success" : "failure";
		return result;
	}
}
