package com.yeqing.mybatis.dao.impl;

import org.springframework.stereotype.Repository;

import com.yeqing.domain.User;
import com.yeqing.mybatis.dao.IUserDAO;
import com.yeqing.mybatis.mapper.UserMapper;
import com.yeqing.util.MybatisUtil;

@Repository
public class UserDAOImpl implements IUserDAO {

	private UserMapper mapper = MybatisUtil.getMapper(UserMapper.class);

	public User checkLogin(String username, String password) {
		return mapper.checkLogin(username, password);
	}

}
