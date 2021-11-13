package com.yeqing.ioc.register.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yeqing.ioc.register.domain.User;
import com.yeqing.ioc.register.service.IUserService;

@Controller
public class UserAction {
	@Autowired
	private IUserService service;

	public void execute(User u) {
		service.register(u);
		System.out.println("注册操作完成");
	}
}
