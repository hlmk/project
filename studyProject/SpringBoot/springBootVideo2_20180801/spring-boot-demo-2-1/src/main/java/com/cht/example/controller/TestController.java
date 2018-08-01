package com.cht.example.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cht.example.bean.User;

@RestController
@RequestMapping("index")
public class TestController {

	@RequestMapping
	public String index() {
		return "Hello World!";
	}
	
	
	@RequestMapping("get")
	public Map<String, String> get(@RequestParam String name){
		Map<String, String> map = new HashMap<>();
		map.put("name", name);
		return map;
	}
	
	@RequestMapping("getUser/{id}/{name}")
	public User getUser(@PathVariable int id, @PathVariable String name) { 	
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setDate(new Date());
		return user;
	}
	
}
