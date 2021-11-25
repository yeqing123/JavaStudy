package com.yeqing.date;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yeqing.vo.User;

//处理日期类型的处理器
@Controller
@RequestMapping("/date")
public class HandleDateController {
	// 从前台以字符串的形式向后台传递一个日期，此时必须使用@DateTimeFormat注解，并设置日期的格式，否则就会出错
	/*
	 * 前台传参：http://localhost/date/test1?date=2021-11-26 21:22:34
	 */
	@RequestMapping("/test1")
	public void test1(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
		System.out.println(date.toLocaleString());
	}

	// 将Data类型一般会定义在其他对象中往后台传递，此时就在Data类型定义的地方贴上@DateTimeFormat注解即可
	/*
	 * 前台传参：http://localhost/date/test2?id=001&username=yeqing&age=18&hiredate=2021-
	 * 11-26%2021:22:34
	 */
	@RequestMapping("/test2")
	public void test2(User u) { // 在User类中定义了一个Date类型的属性
		System.out.println(u);
	}

	// 如果不想贴@DateTimeFormat注解，可以定义一个方法来处理Date类型的数据绑定规则：
	@InitBinder
	public void initBinderDateType(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyy-HH-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	/*
	 * 定义完上述方法后，试着将前两个测试中的@DateTimeFormat注解去掉，看看是否有效。
	 * 这种方式可以一劳永逸的解决Date类型的格式问题，但是还是有一个缺点就是，每个Controller处理器中如果要处理Date类型，
	 * 都需要定义一个相同的方法，解决办法是将这个方法专门定义在一个类中，并在这个类上贴上ControllerAdvice注解，
	 * 只要这个类可以被Spring扫描到就可以生效。
	 */
	
	
	
	

	/*在jsp页面上显示Date类型需要格式化，否则显示风格不符合中国人的习惯，此时需要使用fmt标签，依赖JSTL标签库：
	 * taglibs-standard-spec-x.x.x.jar   和       taglibs-standard-impl-x.x.x.jar
	 * 它们在Tomcat的两个案例的lib中就有。
	 * 然后在JSP页面中使用JSTL标签就可以了：<fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm:ss"/>
	 */
	@RequestMapping("/test3")
	public ModelAndView test3() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("showdate");
		mv.addObject("date", new Date());
		return mv;
	}
	//后台往前台响应JSON时，Date类型问题
	/*
	 * 如果直接以JSON形式显示，出现的日期就是从1970年至今的毫秒数。
	 * 解决方式一：（很恶心）在<mvc:annotation-driven>中做如下配置：
	   <!-- 配置以JSON形式响应时的Date显示风格 -->
	   <mvc:message-converters>
			<bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
				<property name="objectMapper">
					<bean class="com.fasterxml.jackson.databind.ObjectMapper">
						<property name="dateFormat">
							<bean class="java.text.SimpleDateFormat">
								<constructor-arg type="java.lang.String"
									value="yyyy-MM-dd HH:mm:ss" />
							</bean>
						</property>
					</bean>
				</property>
			</bean>
		</mvc:message-converters>
		
     * 解决方式二：在JavaBean中定义Date属性的地方，贴JsonFormat注解，在其中设置pattern属性，如果时区不对还可以设置timezone属性为“GMT+8”（东8区）
	 */
	@RequestMapping("/test4")
	@ResponseBody
	public User test4() {
		User u = new User();
		u.setId(11L);
		u.setUsername("叶青");
		u.setAge(19);
		u.setHiredate(new Date());
		return u;
	}
}
