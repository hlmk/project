package com.cht.servletFilterListenter.dao;

import com.cht.servletFilterListenter.bean.User;

public interface UserDao {
	
	int insert(User user);
	
	int deleteById(Long id);
	
	int updateById(User user);
	
	User selectById(Long id);
	

}
