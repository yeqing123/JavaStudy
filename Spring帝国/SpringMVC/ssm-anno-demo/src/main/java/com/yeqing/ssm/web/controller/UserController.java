package com.yeqing.ssm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yeqing.ssm.domain.User;
import com.yeqing.ssm.service.IUserService;

//处理User对象请求的控制器
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService service;
	
	//请求路径：http://localhost:port/user/list
	@RequestMapping("/list")
	public String listAll(Model m) {
		m.addAttribute("list", service.listAll());
		return "user/list";
	}
	
	@RequestMapping("/delete")
	public String delete(Long id) {
		service.delete(id);
		return "redirect:/user/list";
	}
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(User u) {
		if(u.getId() == null) {  //save
			service.save(u);
		} else {  //update
			service.update(u);
		}
		return "redirect:/user/list";
	}
	@RequestMapping("/input")
	public String input(Long id, Model m) {
		if(id != null) {  //update
			m.addAttribute("u", service.get(id));
		}
		return "user/input";
	}
}
