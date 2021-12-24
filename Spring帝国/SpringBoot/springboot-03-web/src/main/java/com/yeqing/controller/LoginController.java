package com.yeqing.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String checkLogin(
			@RequestParam("username")String username, 
			@RequestParam("password")String password, 
			Model model,
			HttpSession session) {
		
		System.out.println("username: " + username + ", password: " + password);
		if(StringUtils.hasLength(username) && "1234".equals(password)) {
			//将登录用户添加到session中，用作登录验证
			session.setAttribute("loginUser", username);
			return "redirect:/main.html";
		}else {
			model.addAttribute("msg", "用户名或密码错误");
			return "/login.html";
		}
	}
}
