package com.yeqing.web.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping("/edit")
	public String edit(Model m, Long id) {
		if(id != null) {
			Employee e = service.get(id);
			m.addAttribute("e", e);
		}
		return "/employee/edit";
	}
	
	@RequestMapping("/delete")
	public String delete(Long id) {
		service.delete(id);
		return "redirect:/employee/list";
	}
	
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(@Validated Employee e, BindingResult result, Model m) {
		List<ObjectError> errors = result.getAllErrors();
		System.out.println(errors);
		if(errors.size() > 0) {
			m.addAttribute("errors", errors);
			return "/employee/edit";
		}
		if(e.getId() != null) {  //update
			service.update(e);
		}else {
			service.save(e);     //save
		}
		return "redirect:/employee/list";
	}
	
	/*
	 * 定义从前台往后台传输数据时，java.util.Data类型的绑定格式（也可以直接在Employee类中定义Date属性的地方贴@DateTimeFormat注解，并在注解中设置时间格式）
	 * 但是，好像使用注解没有定义数据绑定规则更可靠
	 */
	
	@InitBinder
	private void initBinderDateType(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(sdf, true));
	}
	
}
