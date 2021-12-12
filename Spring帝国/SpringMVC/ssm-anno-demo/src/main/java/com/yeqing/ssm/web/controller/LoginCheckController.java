package com.yeqing.ssm.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yeqing.ssm.service.IUserService;

@Controller
public class LoginCheckController {
	@Autowired
	private IUserService service;
	
	@RequestMapping("/login")
	public String login(String name, String password, HttpSession session) {
		try {
			service.login(name, password);
		} catch(Exception e) {
			session.setAttribute("errorMsg_in_session", e.getMessage());
			return "redirect:/login.jsp";
		}
		return "redirect:/user/list";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session)  {
		session.invalidate();
		return "redirect:login.jsp";
	}
}
