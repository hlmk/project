package com.cht.servletFilterListenter.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cht.servletFilterListenter.bean.User;
import com.cht.servletFilterListenter.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int insert(User user) {
		String sql = "insert into User(id,user_name,password,age,height,date) values(?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,user.getId(),user.getUserName(),user.getPassword(),user.getAge(),user.getHeight(),user.getDate());
	}

	@Override
	public int deleteById(Long id) {
		String sql = "delete from user where id = ?";
		return jdbcTemplate.update(sql,id);
	}

	@Override
	public int updateById(User user) {
		String sql = "update user set user_name = ?,password = ?,age = ?, height = ?, date= ?";
		return jdbcTemplate.update(sql,user.getUserName(),user.getPassword(),user.getAge(),user.getHeight(),user.getDate());
	}

	@Override
	public User selectById(Long id) {
		String sql = "select * from user where id = ? ";
		return jdbcTemplate.queryForObject(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getLong("id"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setAge(rs.getInt("age"));
				user.setHeight(rs.getDouble("height"));
				user.setDate(rs.getDate("date"));
				return user;
			}
			
		});
	}

}
