package com.yeqing.hello;

import com.yeqing.mockmvc.annotation.Controller;
import com.yeqing.mockmvc.annotation.RequestMapper;
import com.yeqing.mockmvc.bean.ModelAndView;

/*
 * 需求：模拟SpringMVC，当用户从浏览器中发出一个请求时，就由前置控制器进行处理，
 * 它会对当前项目中所有控制器类进行扫描，找到与该请求对应的方法及其所在的控制类，
 * 然后调用该方法，获得下一步需要进行操作的信息（一个ModelAndView对象），
 * 然后控制页面进行跳转。后台进行的什么样的操作取决于该请求对应的控制类及其方法！
 */
@Controller   //该注解表示这是一个处理浏览器请求的处理类
public class HelloController {

	@RequestMapper("/hello")
	public ModelAndView sayHello() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/WEB-INF/view/welcome");
		mv.addModel("msg", "你好，我是在模拟SpringMVC！");
		return mv;
	}
	@RequestMapper("/hello2")
	public ModelAndView sayHello2() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/WEB-INF/view/welcome2");
		mv.addModel("msg", "练习模拟SpringMVC，付出就会有回报！");
		return mv;
	}
}
