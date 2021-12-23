package com.yeqing.config;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

//定义自己的语言区域解析器
public class MyLocaleResolver implements LocaleResolver {

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		String language = request.getParameter("l");
		Locale locale = request.getLocale();  //获得默认值
		if(StringUtils.hasLength(language)) {
			//分割language字符串，获得“语言”和“国家”两个内容
			String[] split = language.split("_");
			locale = new Locale(split[0], split[1]);
		}
		return locale;
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		//设置响应字符编码，否则会出现中文乱码
        response.setCharacterEncoding("utf-8");
	}

}
