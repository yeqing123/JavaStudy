package com.yeqing.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.yeqing.util.UserContext;

//登录验证拦截器
public class LoginCheckInterceptor implements HandlerInterceptor {
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
        if(UserContext.getCurrentUser() == null) {  //如果session中没有保存登录用户，则说明需要拦截
        	response.sendRedirect("/login.jsp");
        	return false;  //拦截
        }
		return true;   //放行
	}
}
