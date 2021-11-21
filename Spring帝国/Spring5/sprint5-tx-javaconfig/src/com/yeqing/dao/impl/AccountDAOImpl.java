package com.yeqing.dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yeqing.dao.IAccountDAO;

@Repository
public class AccountDAOImpl implements IAccountDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
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
