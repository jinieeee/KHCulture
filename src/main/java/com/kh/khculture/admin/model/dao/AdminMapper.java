package com.kh.khculture.admin.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.khculture.admin.model.vo.Search;
import com.kh.khculture.member.model.vo.Member;

@Mapper
public interface AdminMapper {
	
	// 전체회원 조회
	List<Member> getAllMemberList(Search search);
	// 전체 회원수 조회
	int getListCount(Search search);

}
