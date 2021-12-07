package com.yeqing.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yeqing.dao.IUserDAO;
import com.yeqing.domain.User;

@Repository
public class UserDAOImpl implements IUserDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}
	
	public User checkLogin(String username, String password) {
		List<User> list = jdbcTemplate.query("SELECT id,username,password FROM user WHERE username = ? AND password = ?", 
				new RowMapper<User>() {
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						User u = new User();
						u.setId(rs.getLong("id"));
						u.setUsername(rs.getString("username"));
						u.setPassword(rs.getString("password"));
						return u;
					}
		}, username, password);
		return list.size()==1?list.get(0):null;
	}

}
