package com.yeqing.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//扩展springMVC
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
	
	@Bean
	public ViewResolver myViewResolver() {
		return new myViewResolver();
	}
	public static class myViewResolver implements ViewResolver {
		@Override
		public View resolveViewName(String viewName, Locale locale) throws Exception {
			return null;
		}
		
	}
}
