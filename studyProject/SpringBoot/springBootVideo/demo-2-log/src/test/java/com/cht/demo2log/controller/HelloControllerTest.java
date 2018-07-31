package com.cht.demo2log.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloControllerTest {

	private MockMvc mockMvc;
	
	@Before
	public void before() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
	}
	
	
	@Test
	public void testHello() throws Exception {
		RequestBuilder reBuilder = get("/hello");
		mockMvc.perform(reBuilder).andExpect(status().isOk()).andExpect(content().string("hello spring boot ..."));
		
	}

}
