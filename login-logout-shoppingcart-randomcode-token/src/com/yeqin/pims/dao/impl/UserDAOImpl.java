package com.yeqin.pims.dao.impl;

import com.yeqin.pims.dao.IUserDAO;
import com.yeqin.pims.domain.User;
import com.yeqin.pims.util.JdbcTemplate;
import com.yeqin.pims.util.handler.BeanHandler;

public class UserDAOImpl implements IUserDAO {

	public User checkLogin(String username) {
	    String sql = "SELECT * FROM t_user WHERE username = ?";
	    return JdbcTemplate.query(sql, new BeanHandler<User>(User.class), username);
	}

}
