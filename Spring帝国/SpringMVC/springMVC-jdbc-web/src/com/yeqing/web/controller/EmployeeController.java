package com.yeqing.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeqing.domain.Employee;
import com.yeqing.service.impl.EmployeeServiceImpl;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl service;
	
	@RequestMapping("/list")
	public String list(Model m) {
		List<Employee> list = service.listAll();
		m.addAttribute("list", list);
		for (Employee e : list) {
			System.out.println(e);
		}
		return "/employee/list";
	}
}
