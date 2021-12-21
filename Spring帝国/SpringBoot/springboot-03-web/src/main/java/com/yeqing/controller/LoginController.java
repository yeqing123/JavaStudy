package com.yeqing.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping("/form_login")
	public String checkLogin(
			@RequestParam("username")String username, 
			@RequestParam("password")String password, 
			Model model,
			HttpSession session) {
		System.out.println("username:" + username + "password: " + password);
		if(!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
			model.addAttribute("msg", "用户名和密码均不可为空");
			return "extra-login";
		}else if("yeqing".equals(username) && "1234".equals(password)) {
			model.addAttribute("msg", "用户名或密码错误");
			return 	"extra-login";
		}else {
			return "index";
		}
	}
}
