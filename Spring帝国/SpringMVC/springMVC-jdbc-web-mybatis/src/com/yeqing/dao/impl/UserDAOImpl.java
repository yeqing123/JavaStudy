package com.yeqing.dao.impl;

import org.springframework.stereotype.Repository;

import com.yeqing.dao.IUserDAO;
import com.yeqing.domain.User;
import com.yeqing.mapper.user.UserMapper;
import com.yeqing.util.MybatisUtil;

@Repository
public class UserDAOImpl implements IUserDAO {

	private UserMapper mapper = MybatisUtil.getMapper(UserMapper.class);

	public User checkLogin(String username, String password) {
		return mapper.checkLogin(username, password);
	}

	public User get(String username, String password) {
		return mapper.get(username, password);
	}

}
