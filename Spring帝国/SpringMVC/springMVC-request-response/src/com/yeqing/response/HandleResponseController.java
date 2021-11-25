package com.yeqing.response;


import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//测试不同的方式进行请求响应
@Controller
@RequestMapping("/response") //窄化请求路径
public class HandleResponseController {

	//普通的方式进行请求转发和重定向
	@RequestMapping("/test1")
	public void test1(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		//请求转发，可以共享数据
		req.setAttribute("msg1", "普通的请求转发");
		req.getRequestDispatcher("/WEB-INF/views/hello.jsp").forward(req, resp);
		//重定向，一般不能共享数据
		req.setAttribute("msg2", "重定向，不能共享数据");
		resp.sendRedirect("/WEB-INF/views/hello.jsp");
	}
	
	//下载文件
	@RequestMapping("/test2")
	public void test2(OutputStream out) throws IOException {
		Path filePath = Paths.get("C://Users/yeqin/Pictures/Camera Roll/family.jpg");
		Files.copy(filePath, out);
	}
	
	
	//常用的方法一：返回ModelAndView对象
	/*
	 如果我们在mvc.xml文件中配置了URL的前缀和后缀，就可以不用再Controller中再写了：
	 <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/views/"/>
		<property name="suffix" value=".jsp"/>
	</bean>
	 */
	@RequestMapping("/test3")
	public ModelAndView test3() {
		ModelAndView mv = new ModelAndView();
//		mv.setViewName("/WEB-INF/views/result.jsp");
		//如果在mvc.xml文件中配置了URL的前缀和后缀，就可以写成：
	    mv.setViewName("result");
		mv.addObject("msg", "返回了一个ModelAndView对象");
		mv.addObject("String类型");  //如果只传入一个参数，它就是要共享的数据，它的key就是数据类型的小写字母，如：string
		return mv;
	}
	
	//常用方法二：返回字符串：逻辑视图的名称（需要传入一个Model类型的参数），这个方法简洁，推荐
	@RequestMapping("/test4")
	public String test4(Model m) {
		m.addAttribute("msg", "有志者事竟成");
		m.addAttribute(new Date().toLocaleString());  //key依然还是数据类型的小写字母
		return "result";
	}
	
	
	//返回字符串，并实现请求转发
	@RequestMapping("/test5")
	public String test5(Model m) {
		m.addAttribute("msg", "实现请求转发");
		return "forward:/WEB-INF/views/result.jsp";  //在返回的字符串中使用forward前缀来实现请求转发，注意：这里必须使用完整的请求路径（前缀+视图名称+后缀）
	}
	
	//返回字符串，并实现重定向，重定向无法共享数据
	@RequestMapping("/test6")
	public String test6(Model m) {
		m.addAttribute("msg", "重定向无法显示该数据");
		return "redirect:/aaa.jsp";  //在返回的字符串中使用redirect前缀来实现请求转发，注意：这里必须使用完整的请求路径（前缀+视图名称+后缀）
	}
	/*
	 * 请求转发与重定向的比较
	    
	                           共享数据       地址栏发生变化     表单的重复提交
	         -----------------------------------
                     请求转发： 可以                   不变化                          会                          
                         重定向：不能？                 变化                          不会           
	 * 
	 * spring从3.1开始可以使用Flash属性，让重定向共享数据，但是仅限于Controller之间的共享，不能共享到jsp中
	 */
	@RequestMapping("/a")
	public String a(RedirectAttributes ra) {
		ra.addAttribute("msg1", "Hello World");
		ra.addFlashAttribute("msg2", "使用了Flash属性");
		return "redirect:/response/b";
	}
	@RequestMapping("/b")
	public ModelAndView b(String msg1, @ModelAttribute("msg2") String msg2) {
		//重定向共享数据只能在Controller之间共享
		System.out.println("msg1: " + msg1);
		System.out.println("msg2: " + msg2);
		return null;
	}
}
