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
		registry.addViewController("/").setViewName("/login.html");
		//不知道为什么，在拦截器LoginHendlerInterceptor中执行请求转发时不能直接转发到login.html，必须要在这里进行处理，否则会报404
		registry.addViewController("/goBackLogin").setViewName("/login.html");  
		registry.addViewController("/loginLanguage").setViewName("/login.html");
		registry.addViewController("/yeqing").setViewName("/");
	}

	// 添加一个登录检查拦截器
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginHandlerInterceptor()) //
				.addPathPatterns("/**") //
				.excludePathPatterns("/", "/login", "/goBackLogin", "/loginLanguage", "/login.html", "/css/**", "/js/**", "/images/**", "/fonts/**");
	}
	
	@Bean  //将MyLocaleResolver作为Bean交给spring帮我们管理
	public LocaleResolver localeResolver() {
		return new MyLocaleResolver();
	}
	
}
