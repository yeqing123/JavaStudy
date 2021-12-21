package com.yeqing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//配置自己的springmvc，添加主页视图控制器
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

	//添加主页视图控制
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("/extra-login");;
		registry.addViewController("/login.html").setViewName("extra-login");
	}
}
