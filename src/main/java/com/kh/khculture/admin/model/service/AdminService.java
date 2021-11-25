package com.kh.khculture.admin.model.service;

import java.util.Map;

import com.kh.khculture.admin.model.vo.Search;

public interface AdminService {
	// 회원 조회
	Map<String, Object> getAllMemberList(Search search);
	
}
