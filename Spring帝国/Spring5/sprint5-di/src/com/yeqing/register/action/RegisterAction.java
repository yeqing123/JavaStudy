package com.yeqing.register.action;


import com.yeqing.register.domain.User;
import com.yeqing.register.service.UserService;

//模拟Struts2的Action/Spring MVC
public class RegisterAction {
	private UserService service;

	public void setService(UserService service) {
		this.service = service;
	}

	public void excute(User u) {
		service.regist(u);
		System.out.println("用户注册操作完成");
	}
}
