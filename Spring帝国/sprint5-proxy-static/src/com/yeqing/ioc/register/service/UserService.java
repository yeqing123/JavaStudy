package com.yeqing.ioc.register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeqing.ioc.register.dao.IUserDAO;
import com.yeqing.ioc.register.domain.User;

@Service
public class UserService implements IUserService {
	@Autowired
	private IUserDAO dao;

	@Override
	public void register(User u) {
		System.out.println("用户注册操作");
		dao.save(u);
	}

}
