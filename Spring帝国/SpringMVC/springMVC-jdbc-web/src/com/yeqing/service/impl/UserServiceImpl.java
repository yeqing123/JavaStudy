package com.yeqing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeqing.dao.IUserDAO;
import com.yeqing.domain.User;
import com.yeqing.service.IUserService;
import com.yeqing.util.UserContext;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDAO userDao;

	public void login(String username, String password) {
		User current = userDao.checkLogin(username, password);
		if(current == null) {   //如果current为空，说明登录不成功，抛出一个异常
			throw new RuntimeException("账号或密码错误");
		}
		UserContext.setCurrentUser(current);  //否则，登录成功，保存当前用户到session中
	}
}
