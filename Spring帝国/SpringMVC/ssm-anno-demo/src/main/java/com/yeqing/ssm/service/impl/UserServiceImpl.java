package com.yeqing.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.yeqing.ssm.domain.User;
import com.yeqing.ssm.mappers.UserMapper;
import com.yeqing.ssm.service.IUserService;
import com.yeqing.ssm.util.UserContext;

@Service
@EnableTransactionManagement
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public void save(User u) {
		userMapper.insert(u);
		//int a = 1 / 0;
	}

	@Override
	public void delete(Long id) {
		userMapper.deleteById(id);
	}

	@Override
	public void update(User u) {
		userMapper.updateById(u);
	}

	@Override
	public User get(Long id) {
		return userMapper.selectById(id);
	}

	@Override
	public List<User> listAll() {
		return userMapper.selectAll();
	}

	@Override
	public void login(String name, String password) {
		User current = userMapper.checkLogin(name, password);
		if(current != null) {
			UserContext.setCurrentUser(current);
		}else {
			throw new  RuntimeException("输入的用户名或密码错误");
		}
	}

}
