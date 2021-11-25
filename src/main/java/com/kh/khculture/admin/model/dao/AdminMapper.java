package com.kh.khculture.admin.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.khculture.admin.model.vo.Search;
import com.kh.khculture.member.model.vo.Member;

@Mapper
public interface AdminMapper {
	
	// 회원 조회
	List<Member> getPageMemberList(Search search);
	
	// 전체 회원수 조회
	int getListCount();
	
	// 페이징 처리 회원수
	int getSearchListCount();
	
	// 검색 조건에 맞는 회원수
	List<Member> getAllMemberList(Search search);

}
