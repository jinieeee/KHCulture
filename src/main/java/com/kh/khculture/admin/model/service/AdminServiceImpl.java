package com.kh.khculture.admin.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.khculture.admin.model.dao.AdminMapper;
import com.kh.khculture.admin.model.vo.Notice;
import com.kh.khculture.admin.model.vo.Search;
import com.kh.khculture.common.PageInfo;
import com.kh.khculture.main.model.vo.MainImage;
import com.kh.khculture.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class AdminServiceImpl implements AdminService{

	private AdminMapper adminMapper;
	
	@Autowired
	public AdminServiceImpl(AdminMapper adminMapper) {
		this.adminMapper = adminMapper;
	}
	
	// 전체회원 조회
	@Override
	public Map<String, Object> getAllMemberList(Search search) {
		Map<String, Object> map = new HashMap<>();
		PageInfo pi = null;
		// 전체 회원수
		int listCount = adminMapper.getListCount();
		
		// 검색조건에 맞는 회원수
		List<Member> allSearchList = adminMapper.getAllMemberList(search);
		
		// 페이징 처리
		if((Integer)search.getPage() != null) {
			// searchListCount = adminMapper.getSearchListCount();
			pi = new PageInfo(search.getPage(), allSearchList.size(), 10, 20);
			search.setStartRow((pi.getPage() - 1) * pi.getBoardLimit() + 1);
			search.setEndRow(search.getStartRow() + pi.getBoardLimit() - 1);
		}

		List<Member> memberList = adminMapper.getPageMemberList(search);
	
		map.put("pi", pi);
		map.put("memberList", memberList);
		map.put("searchListcount", allSearchList.size());
		map.put("listCount", listCount);
		return map;
	}
	
	// 비밀번호 초기화
	@Transactional
	@Override
	public int pwdReset(List<Integer> mnoList) {
		int result = 0;
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		// 비밀번호 암호화 처리 : 1234
		String newPwd = passwordEncoder.encode("1234");
		for(Integer mno : mnoList) {
			Map<String, Object> map = new HashMap<>();
			map.put("mno", mno);
			map.put("newPwd", newPwd);
			result += adminMapper.pwdReset(map);
		}
		
		return result == mnoList.size()? 1: 0;
	}
	
	// 계정 삭제
	@Transactional
	@Override
	public int deleteAcc(List<Integer> mnoList) {
		int result = 0;
		for(Integer mno : mnoList) {
			Map<String, Object> map = new HashMap<>();
			map.put("mno", mno);
			result += adminMapper.deleteAcc(map);
		}
		return result == mnoList.size()? 1: 0;
	}
	
	// 등급 변경
	@Transactional
	@Override
	public int roleUpdate(List<Integer> mnoList) {
		int result1 = 0;
		int result2 = 0;
		int result3 = 0;
		for(Integer mno : mnoList) {
			Map<String, Object> map = new HashMap<>();
			map.put("mno", mno);
			result1 += adminMapper.deleteRole(map);
			result2 += adminMapper.insertRole1(map);
			result3 += adminMapper.insertRole2(map);
		}
		return (result2 == result3)? 1: 0;
	}
	
	// 공지사항 관리 목록
	@Override
	public Map<String, Object> getNoticeList(Search search) {
		Map<String, Object> map = new HashMap<>();
		PageInfo pi = null;
		int noticeListCount = 0;
		
		// 페이징 처리
		if((Integer)search.getPage() != null) {
			noticeListCount = adminMapper.getNoticeListCount(search);
			pi = new PageInfo(search.getPage(), noticeListCount, 10, 20);
			search.setStartRow((pi.getPage() - 1) * pi.getBoardLimit() + 1);
			search.setEndRow(search.getStartRow() + pi.getBoardLimit() - 1);
		}

		List<Notice> noticeList = adminMapper.getPageNoticeList(search);

		map.put("pi", pi);
		map.put("noticeList", noticeList);
		return map;
	}
	
	// 메인이미지 등록
	@Override
	public int enrollMainImage(MainImage mi) {
		int result = adminMapper.enrollMainImage(mi);
		return result;
	}
	
	// 메인이미지 삭제
	@Override
	public int deleteMain(int nno) {
		return adminMapper.deleteMain(nno);
	}
}
