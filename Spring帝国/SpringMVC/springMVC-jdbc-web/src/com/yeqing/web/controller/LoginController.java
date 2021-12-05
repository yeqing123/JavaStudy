package com.yeqing.web.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yeqing.service.IUserService;

//用户登录控制
@Controller
public class LoginController {
	
	private static final String ERRORMSG_IN_SESSION = "errorMsg_in_session";
	@Autowired
	IUserService userService;
	
	@RequestMapping("/login")
	public String login(String username, String password, HttpSession session) {
		try {
			userService.login(username, password);
		}catch(Exception e) {
            session.setAttribute(ERRORMSG_IN_SESSION, e.getMessage());			
            return "redirect:/login.jsp";
		}
		return "redirect:/employee/list";
	}
}
