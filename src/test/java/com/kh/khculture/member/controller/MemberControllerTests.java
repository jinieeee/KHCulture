package com.kh.khculture.member.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
public class MemberControllerTests {
	@Autowired
	private MemberController memberController;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
	}
	
	@Test
	public void testAccSecession() throws Exception {
		mockMvc.perform(delete("/member/accSecession"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("/member/accSecession"));
	}
}
