package com.yeqing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeqing.dao.IUserDAO;
import com.yeqing.domain.User;
import com.yeqing.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDAO userDao;

	public User checkLogin(String username, String password) {
		return userDao.checkLogin(username, password);
	}
}
