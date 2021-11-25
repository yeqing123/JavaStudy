package com.yeqing.request;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeqing.vo.FormBean;
import com.yeqing.vo.User;

//处理请求
@Controller
@RequestMapping("/request") //还是用来窄化请求路径
public class HandleRequestController {

	/*
	 * ServletContext的作用域在整个应用程序，因此可以定义成成员变量，
	 * 除此之外其他的都不要定义成成员变量，以免造成不安全，因为Spring中所有的控制器都是单例的
	 */
	@Autowired
	private ServletContext context; 
	@RequestMapping("/test1")
	public void test1(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		System.out.println(request);
		System.out.println(response);
		System.out.println(session);
		System.out.println(context); 
		System.out.println(request.getHeader("User-Agent"));  //获取名为“User-Agent”的请求头
	}
	/*
	 * 如果直接通过形参向控制器传递请求参数，则如果请求参数的名称与形参的名称不一致，
	 * 可以使用RequestParam注解注明，还可以配置required属性表示该形参为不是必须的
	 * 如果传递过来的中文出现了乱码，则要在web.xml文件中配置一个CharacterEncodingFilter的bean用于设置字符编码过滤器
	 */
	@RequestMapping("/test2")
	public void test2(@RequestParam(value = "username", required = false)String name, @RequestParam(value = "uage", required=false)Integer age) {
		System.out.println("name: " + name);
		System.out.println("age: " + age);
	}
	
	//使用RESTfull风格传参
	/*
	 * 传统的请求传递参数的方式：/request/test3?id=11
	 * RESTfull风格的传参方式：/request/test3/11    
	 *     需要在RequestMapping中使用 {参数类型} 的形式在URL中加入占位符，并使用一个PathVariable注解将URL中的占位符绑定到方法的形参中
	 */
	@RequestMapping("/test3/{id}")
	public void test3(@PathVariable("id")Long id) {
		System.out.println("id = " + id);
	}
	
	//接收一个参数有多个值，URL的形式：http://localhost/request/test4?ids=10&ids=11&ids=12
	/*
	 * 方法一：直接使用数组；方式二：使用List集合，但是不能直接使用，必须将这个List集合封装到一个对象中才能用.
	 * 如果使用List集合，URL中必须带上索引：http://localhost/request/test4?ids[0]=10&ids[1]=11&ids[2]=12
	 * 
	 * 总结：操作一个参数有多个值的情况，一般直接使用数组，或使用JavaBean对象来封装数据。
	 */
	@RequestMapping("/test4")
	public void batchDelete(FormBean fb) {
		System.out.println(fb.getIds());
	}
	
	/*
	 * 使用JavaBean来封装数据，在URL中直接给与对象属性对应的请求参数赋值来传入数据，spring就能自动将它们封装到JavaBean中：
	 * http://localhost/request/test5?id=11&username=yeqing&age=17
	 * 
	 * 后台打印结果：User(id=11, username=yeqing, age=17)
	 */
	@RequestMapping("/test5")
	public void usedJavaBean(User u) {
		System.out.println(u);
	}
	
	/**
	 * 总结：
	 * 	1、直接把请求参数封装成JavaBean，该方式最常用
	 * 	2、配置解决中文乱码的过滤器
	 *  3、简单类型参数，搭配@RequestParam注解，该方式不常用
	 *  4、使用数组接收参数，偶尔使用
	 *  5、RESTfull风格传参，将来也许会很流行
	 */
	
	//ModelAttribute注解的使用
	/*
	 * ModelAttribute注解的用法：
	 * 1：标注在请求方法的形参上，用来指定该形参对应的key，此时该形参必须是一个符合类型（非简单类型），
	 * 		并且不可以标注在Model类型上，只能标注在自定义的类型上，否则会出错。
	 * 2：直接标注在一个普通方法上，此时服务器在调用Controller中定义的其他任何请求方法时都会先执行该方法，再处理请求
	 */
	@RequestMapping("/test6")
	public String test6(@ModelAttribute("myUser")User u) {  //如果标注了形参的key，默认的类型首字母小写的用法就会失效
		System.out.println("处理请求...");
		u.setUsername("张三");
		u.setAge(17);
		return "result";
	}
	//标注在一般的方法上
	@ModelAttribute
	public void preExecute() {
		System.out.println("前置执行...");
	}
	/*
	 * 当Controller方法返回一个对象时，缺省会把当前请求的url作为逻辑视图名称，并共享当前的返回对象。一般返回对象是一个JavaBean，
	 * 用于JSON操作，该操作还必须依赖jackson库，还需要在方法上贴一个ResponseBody注解。
	 */
	@RequestMapping("/test7")
	@ResponseBody
	public User test7() {
		User u = new User();
		u.setId(11L);
		u.setUsername("叶青");
		u.setAge(17);
		return u;
	}
}
