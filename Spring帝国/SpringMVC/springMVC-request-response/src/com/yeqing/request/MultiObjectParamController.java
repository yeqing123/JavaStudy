package com.yeqing.request;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yeqing.vo.Cat;
import com.yeqing.vo.Dog;

//数据保定的流程（核心组件DataBinder）
/*
 * 处理多个对象封装， 在一个JSP页面里面包含了多个对象的属性的表单，需要把表单中的数据封装到多个对象中去 的时候，
 * 如果各个对象都有相同的属性，此时要出问题；因为它不知道该把哪个参数封装到哪一个对象中去，所以封装的规则需要我们来设置。
 * 解决办法：另外定义一个方法，名称不限，要在该方法上贴一个@InitBinder注解，它用于对数据绑定进行注册，value值就是要封装的对象名，
 * 方法中需要一个类型为WebDataBinder的形参，它是DataBinder的子类，专门用于数据绑定，用于完成由请求参数到JavaBean的属性绑定。
 */
@Controller
@RequestMapping("/request")
public class MultiObjectParamController {

	@RequestMapping("/save")
	public ModelAndView save(Cat cat, Dog dog) {
		System.out.println(cat);
		System.out.println(dog);
		return null;
	}
	
	@InitBinder("cat")  //该注解标注的方法不能有返回值，它必须声明为void
	public void initBindCatType(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("cat.");
	}
	@InitBinder("dog")
	public void initBindDogType(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("dog.");
	}
}
