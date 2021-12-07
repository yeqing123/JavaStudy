package com.yeqing.util;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yeqing.domain.User;

//获取或设置当前登录用户的工具类
/*
 * RequestContextHolder类只能从当前web请求中获取request和session对象。
 * 如果没有发出web请求（就是从浏览器向后台发出的请求），该类返回的将是null
 */
public class UserContext {

	private static final String USER_IN_SESSION = "user_in_session";

	private static HttpSession getSession() {
		return ((ServletRequestAttributes)
				(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
	}
	
	public static void setCurrentUser(User u) {
		if(u != null) {
			getSession().setAttribute(USER_IN_SESSION, u);
		} else {
			getSession().invalidate();
		}
	}
	
	public static User getCurrentUser() {
		return (User) getSession().getAttribute(USER_IN_SESSION);
	}
	
}
