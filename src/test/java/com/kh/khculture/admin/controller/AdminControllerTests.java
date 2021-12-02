package com.kh.khculture.admin.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.kh.khculture.configuration.BootKhcultureFinalprojectApplication;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BootKhcultureFinalprojectApplication.class})
public class AdminControllerTests {
	
	@Autowired
	private AdminController adminController;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
	}
	
	@Test
	public void testFindAllMember() throws Exception {
		mockMvc.perform(get("/admin/memberList")) // @RequestMapping url과 return String이 동일하면 오류가 발생하므로 변경하여 테스트
			.andExpect(status().isOk()) // 통신 상태를 예상
			.andExpect(forwardedUrl("/admin/memberList")) // url이 매칭되는지 테스트
			.andDo(print());
	}
}
