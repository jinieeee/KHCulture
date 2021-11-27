package com.kh.khculture.admin.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.khculture.admin.model.vo.Notice;
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
	
	// 비밀번호 초기화
	int pwdReset(Map<String, Object> map);

	// 계정 삭제
	int deleteAcc(Map<String, Object> map);
	
	// 권한 삭제
	int deleteRole(Map<String, Object> map);

	// 일반회원 권한 부여
	int insertRole1(Map<String, Object> map);
	
	// 관리자 권한 부여
	int insertRole2(Map<String, Object> map);
	
	// 공지사항 게시글수
	int getNoticeListCount(Search search);
	
	// 페이징 처리된 공지사항
	List<Notice> getPageNoticeList(Search search);
	
}
