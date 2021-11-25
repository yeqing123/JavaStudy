package com.yeqing.request;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

//处理其他请求
@Controller
@RequestMapping("/other")
@SessionAttributes("errorMsg")  //该注解表示在处理器中key设置为“errorMsg”的数据是保存在HttpSession中共享的
public class OtherRequestController {

	//获得指定名称的请求头信息
	@RequestMapping("/test1")
	public void getRequestHeader(@RequestHeader("User-Agent") String header) {
		System.out.println(header);
	}
	//获取指定名称的Cookie信息
	@RequestMapping("/test2")
	public void getCookie(@CookieValue("JSESSIONID") String cookie) {
		System.out.println(cookie);
	}
	/*
	 * 操作HttpSession，默认情况下共享数据会放到request中共享，
	 * 如果想在session中共享数据，就要在Controller类上使用注解SessionAttributes来注册
	 */
	@RequestMapping("/test3")
	public String operateHttpSession(Model m) {
		m.addAttribute("errorMsg", "错误的内容提示");  //共享的数据保存在HttpSession中
		return "redirect:/abc.jsp";
	}
	
}
