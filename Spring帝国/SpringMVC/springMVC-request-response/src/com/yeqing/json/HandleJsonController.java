package com.yeqing.json;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeqing.vo.User;

@Controller
@RequestMapping("/json")
public class HandleJsonController {

	/*
	 * 处理JSON的注解：
	 * 		ResponseBody：处理响应，把对象转换为JSON字符串
	 * 			贴在方法上：只对当前方法做JSON处理
	 * 		         贴在类上：    会对当前类中所有的方法做JSON处理
	 * 
	 * 		RestController = Controller = ResponseBody
	 * 
	 * 		RequestBody：处理请求，用于读取HTTP请求的内容，把JSON格式的请求数据封装成对象。
	 * 
	 * 一般的请求的数据格式：
	 * 		application/x-www-form-urlencoded: 传统的key-value格式，处理起来非常方便，不需要RequestBody都可以，贴上也可以
	 * 		application/multipart: 文件上传的请求，springMVC装饰设计模式，既能处理文件上传，也能处理普通表单数据。
	 * 		application/json:  参数是JSON格式的，此时必须使用RequestBody
	 */
	// 把单个对象或Map转换成JSON格式
	@RequestMapping("/test1")
	@ResponseBody
	public User test1() {
		User u = new User();
		u.setId(13L);
		u.setUsername("叶青");
		u.setAge(19);
		return u;
	}

	// 把多个对象转换成JSON
	@RequestMapping("/test2")
	@ResponseBody
	public List<User> test2() {
		User u = new User();
		u.setId(13L);
		u.setUsername("叶青");
		u.setAge(19);
		return Arrays.asList(u, u, u);
	}
	/*
	 * 返回一个String，如果在方法上贴了ResponseBody，它会把返回的字符串不当做逻辑视图的名称，而是当作JSON格式的数据。
	 */
	@RequestMapping(value = "/test3")
    @ResponseBody
	public String test3() {
		return "你好，JSON";
	}
	//把一个Map对象转换成JSON格式
	@RequestMapping("/test4")
	@ResponseBody
	public Map<String, Object> test4() {
		Map<String, Object> map = new HashMap<>();
		map.put("id", 11L);
		map.put("name", "叶青");
		map.put("age", 18);
		return map;
	}
}
