package com.yeqing.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


//Session对象的创建和销毁监听器
@WebListener
public class SessionListener implements HttpSessionListener {
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("创建会话（session）对象:" + se.getSession().toString());
		
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("销毁会话（session）对象:" + se.getSession().toString());
		System.out.println("===================================");
	}

}
