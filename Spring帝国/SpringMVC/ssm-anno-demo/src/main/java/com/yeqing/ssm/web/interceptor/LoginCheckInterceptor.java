package com.yeqing.ssm.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.yeqing.ssm.util.UserContext;

//用户请求拦截器，用户检查使用已经登录
public class LoginCheckInterceptor implements HandlerInterceptor {
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(UserContext.getCurrentUser() == null) {
			response.sendRedirect("/login.jsp");
			return false;   //没有登陆，拦截
		}
		return true;  //已经登陆，放行
	}
}
