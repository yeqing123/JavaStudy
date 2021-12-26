package com.yeqing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yeqing.interceptor.LoginHandlerInterceptor;

//配置自己的springmvc，添加自定义的视图控制器
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

	// 添加主页视图控制
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/main.html").setViewName("/index.html");
		registry.addViewController("/login").setViewName("/login.html");
		registry.addViewController("/goBackLogin").setViewName("/login.html");
	}

	// 添加一个登录检查拦截器
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginHandlerInterceptor()) //
				.addPathPatterns("/**") //
				.excludePathPatterns("/login","/goBackLogin", "/user/login", "/login.html", "/css/**", "/js/**", "/images/**", "/fonts/**");
	}
	
	@Bean  //将MyLocaleResolver作为Bean交给spring帮我们管理
	public LocaleResolver localeResolver() {
		return new MyLocaleResolver();
	}
	
}
