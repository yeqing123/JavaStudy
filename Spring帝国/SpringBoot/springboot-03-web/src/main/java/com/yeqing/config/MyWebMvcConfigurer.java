package com.yeqing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yeqing.interceptor.LoginHandlerInterceptor;

//配置自己的springmvc，添加主页视图控制器
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

	// 添加主页视图控制
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/main.html").setViewName("/index.html");
		registry.addViewController("/").setViewName("/login.html");
	}

	// 添加一个登录检查拦截器
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginHandlerInterceptor()) //
				.addPathPatterns("/main.html") //
				.excludePathPatterns("/yeqing/**");
//						"/index.html", "/css/**", "/js/**", "/images/**", "/fonts/**");
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/")
				.addResourceLocations("classpath:/static/");
	}
}
