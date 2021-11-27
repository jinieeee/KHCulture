package com.kh.khculture.admin.model.service;

import java.util.List;
import java.util.Map;

import com.kh.khculture.admin.model.vo.Search;

public interface AdminService {
	// 회원 조회
	Map<String, Object> getAllMemberList(Search search);
	
	// 비밀번호 초기화 
	int pwdReset(List<Integer> mnoList);
	
	// 계정 삭제
	int deleteAcc(List<Integer> mnoList);
	
	// 등급 변경
	int roleUpdate(List<Integer> mnoList);

	// 공지사항 목록
	Map<String, Object> getNoticeList(Search search);
	
}
