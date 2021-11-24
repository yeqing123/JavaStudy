package com.yeqing.hello;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller    //该注解表示这个类是一个mvc处理器
public class HelloController {

	@RequestMapping("/hello")  //该注解可以贴在方法上，也可以贴在类上，贴在类上用于窄化url，即方法上的url路径必须以类上的url路径作为父路径
	public ModelAndView sayHello(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("sayHello...");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/WEB-INF/views/welcome.jsp");
		mv.addObject("msg", "使用注解来配置SpringMVC");
		return mv;
	}
	
}
