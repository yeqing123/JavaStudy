package com.yeqing.register.service;


import com.yeqing.register.dao.IUserDAO;
import com.yeqing.register.domain.User;

public class UserService implements IUserService {

	private IUserDAO dao;
	public void setDao(IUserDAO dao) {
		this.dao = dao;
	}
	public void regist(User u) {
		try {
			dao.save(u);
			System.out.println("用户注册操作");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
