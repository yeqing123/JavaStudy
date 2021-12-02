package com.yeqing.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yeqing.domain.User;
import com.yeqing.service.IUserService;

//用户登录控制
@Controller
public class LoginController {
	
	@Autowired
	IUserService userService;
	
	public String login(String username, String password, HttpSession session) {
		User u = userService.checkLogin(username, password);
		if(u != null) {
			return "/employee/list";
		}else {
		    session.setAttribute("errorMsg_in_session", "您输入的账号或密码错误");
		    return "redirect:/list.jsp";
		}
	}
}
