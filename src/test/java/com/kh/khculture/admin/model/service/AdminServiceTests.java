package com.kh.khculture.admin.model.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.kh.khculture.admin.model.service.AdminService;
import com.kh.khculture.admin.model.vo.Search;
import com.kh.khculture.configuration.BootKhcultureFinalprojectApplication;
import com.kh.khculture.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

// JUnit을 사용한 단위 테스트

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BootKhcultureFinalprojectApplication.class})
public class AdminServiceTests {
	
	@Autowired
	private AdminService adminService;
	
	@Test
	public void testFindAllMember() {
		// 전체회원 목록 조회 테스트
		Search search = new Search();
		// 검색조건 : 회원등급(전체), 페이지(1) 기본값 설정
		search.setAuthorityCode(0);
		search.setPage(1);
		Map<String, Object> result = adminService.getAllMemberList(search);
		
		List<Member> memberList = (List<Member>) result.get("memberList");
		
		for(Member member : memberList) {
			System.out.println(member);
		}
		
		assertNotNull(result.get("memberList"));
	}
}
