package com.yeqing.dao.impl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.yeqing.dao.IAccountDAO;

public class AccountDAOImpl implements IAccountDAO {

	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}
	public void transferOut(Long outId, Integer money) {
		jdbcTemplate.update("UPDATE account SET balance = balance - ? WHERE id = ?", money, outId);
	}

	public void transferIn(Long inId, Integer money) {
		jdbcTemplate.update("UPDATE account SET balance = balance + ? WHERE id = ?", money, inId);
	}

}
