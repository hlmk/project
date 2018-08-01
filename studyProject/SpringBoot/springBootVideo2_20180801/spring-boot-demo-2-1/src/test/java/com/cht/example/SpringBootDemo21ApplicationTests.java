package com.cht.example;


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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cht.example.controller.TestController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemo21ApplicationTests {

	private MockMvc mvc;
	
	@Before
	public void before() {
		this.mvc = MockMvcBuilders.standaloneSetup(new TestController()).build();
	}
	
	@Test
	public void contextLoads() {
		RequestBuilder req = get("/index");
		try {
			mvc.perform(req).andExpect(status().isOk()).andExpect(content().string("Hello World!"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
