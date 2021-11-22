package com.kh.khculture.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.khculture.member.model.service.MemberService;
import com.kh.khculture.member.model.vo.Member;
import com.kh.khculture.member.model.vo.PwdHint;
import com.kh.khculture.member.model.vo.RandomNum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {
	
	private MemberService memberService;
	private RandomNum randomNum;
	
	@Autowired
	public MemberController(MemberService memberService, RandomNum randomNum) {
		this.memberService = memberService;
		this.randomNum = randomNum;
	}
	
	@RequestMapping("login")
	public void login() {}
	
	@GetMapping("signUpMember")
	public void signUpMember() {}
	
	@GetMapping("findId")
	public void findId() {}
	
	@GetMapping("findIdResult")
	public void findIdResult() {}
	
	@GetMapping("findPwd")
	public void findPwd() {}
	
	@GetMapping("findPwdResult")
	public void findPwdResult() {}
	
	@PostMapping("signUpMember")
	public String signUpMember(Member member, RedirectAttributes rttr/*, HttpSession session */) {
		log.info("회원 정보 조회 : {}", member.getAddress());
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
		int authCode = randomNum.getRandomNum();
		// 문자 전송
		// memberService.sendAuthCode(phone, authCode);
		return Integer.toString(authCode);
	}

	// 아이디 중복 확인
	@PostMapping("checkId")
	@ResponseBody
	public String checkId(String userId) {
		
		String result = (memberService.checkId(userId) == 0) ? "success" : "failure";
		return result;
	}
	
	// 비밀번호 재설정용 계정 조회
	@PostMapping("findPwd")
	public String findPwd(Member member, RedirectAttributes rttr) {
		// log.info("비밀번호 재설정 : {}", member);
		String returnUrl = "";
		String userId = memberService.findPwd(member);
		// log.info("비밀번호 재설정 id : {}", userId);
		if(userId != null) {
			rttr.addFlashAttribute("userId", userId);
			returnUrl = "redirect:/member/findPwdResult";
		} else {
			rttr.addFlashAttribute("msg", "일치하는 계정을 찾을 수 없습니다");
			returnUrl = "redirect:/member/findPwd";
		}
		return returnUrl;
	}
	
	// 비밀번호 재설정
	@PostMapping("findPwdResult")
	public String findPwdResult(Member member, RedirectAttributes rttr) {
		
		int result = memberService.resetPwd(member);
		String msg = result > 0? "비밀번호가 재설정되었습니다. 재설정한 비밀번호로 로그인하세요":
								"비밀번호 재설정에 실패하였습니다. 재시도해주세요";
		log.info("{}", msg);
		
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:/member/findPwdResult";
	}
	
	// 아이디 찾기용 계정 조회
	@PostMapping("findId")
	public String findId(Member member, RedirectAttributes rttr, Model model) {
		String returnUrl = "";
		Member findResult = memberService.findId(member);
		if(findResult.getId() != null) {
			// rttr.addFlashAttribute("findResult", findResult);
			model.addAttribute("findResult", findResult);
			returnUrl = "/member/findIdResult";
		} else {
			rttr.addFlashAttribute("msg", "일치하는 계정을 찾을 수 없습니다");
			returnUrl = "redirect:/member/findId";
		}
		return returnUrl;
	}
}
