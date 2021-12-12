package com.yeqing.ssm.util;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yeqing.ssm.domain.User;

//获取当前session，以及在session中设置或获取正在登录的用户信息
public class UserContext {

	private static String USER_IN_SESSION = "user_in_session";
	
	public static HttpSession getSession() {
		return ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
	}
	//设置登录用户到session中
	public static void setCurrentUser(User u) {
		if(u != null) {
			getSession().setAttribute(USER_IN_SESSION , u);
		} else {
			getSession().invalidate();
		}
	}
	//获取session保存的登录用户
	public static User getCurrentUser() {
		return (User) getSession().getAttribute(USER_IN_SESSION);
	}
}
