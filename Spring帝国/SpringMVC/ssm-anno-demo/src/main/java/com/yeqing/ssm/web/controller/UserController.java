package com.yeqing.ssm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yeqing.ssm.service.IUserService;

//处理User对象请求的控制器
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService service;
	
	@RequestMapping("/list")
	public String listAll(Model m) {
		m.addAttribute("list", service.listAll());
		return "user/list";
	}
}
