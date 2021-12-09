package com.yeqing.ssm.service.impl;

import java.util.List;


import com.yeqing.ssm.domain.User;
import com.yeqing.ssm.mapper.UserMapper;
import com.yeqing.ssm.service.IUserService;

public class UserServiceImpl implements IUserService {
	
	private UserMapper userMapper;
	
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	public void save(User u) {
		userMapper.insert(u);
		int a = 1/0;
	}

	public void delete(Long id) {
		userMapper.deleteById(id);
	}

	public void update(User u) {
		userMapper.updateById(u);
	}

	public User get(Long id) {
		return userMapper.selectById(id);
	}

	public List<User> listAll() {
		return userMapper.selectAll();
	}

}
