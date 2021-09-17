package com.yeqing.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

//请求事件监听器
@WebListener
public class ServletRequestListenerDemo implements ServletRequestListener {

	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("销毁请求对象:" + sre.getServletRequest().toString());
		System.out.println("========================================");
	}

	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("创建请求对象:" + sre.getServletRequest().toString());
	}

}
