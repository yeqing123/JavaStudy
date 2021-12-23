package com.yeqing.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

//定义登录处理拦截器
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object loginUser = request.getSession().getAttribute("loginUser");
		if(loginUser != null) {
			return true;
		}else {
			System.out.println("LoginHandlerInterceptor.preHandle()==>" + request.getRequestURI());
			request.setAttribute("msg", "没有访问权限，请先登录");
			request.getRequestDispatcher("goBackLogin").forward(request, response);
			return false;
		}
	}
}
