package com.kh.khculture.admin.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.khculture.admin.model.dao.AdminMapper;
import com.kh.khculture.admin.model.vo.Search;
import com.kh.khculture.common.PageInfo;
import com.kh.khculture.member.model.vo.Member;

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
		int listCount = 0;
		
		// 페이징 처리
		if((Integer)search.getPage() != null) {
			listCount = adminMapper.getListCount(search);
			pi = new PageInfo(search.getPage(), listCount, 10, 10);
			search.setStartRow((pi.getPage() - 1) * pi.getBoardLimit() + 1);
			search.setEndRow(search.getStartRow() + pi.getBoardLimit() - 1);
		}
		
		List<Member> memberList = adminMapper.getAllMemberList(search);

		map.put("pi", pi);
		map.put("memberList", memberList);
		map.put("listCount", listCount);
		return map;
	}

}
