package com.yeqing.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	@RequestMapping("/hello")
	public String hello(Model m) {
		m.addAttribute("msg", "<h1>Hello, springboot!</h1>");
		m.addAttribute("users", Arrays.asList("yeqing", "supermen"));
		m.addAttribute("mood", false);
		return "hello";
	}
}
