package com.yeqing.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {

	public void attributeAdded(HttpSessionBindingEvent se) {
		System.out.println("在session作用域中添加属性：" + se.getName());
	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		System.out.println("在session作用域中删除属性：" + se.getName());
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		System.out.println("在session作用域中替换属性" + se.getName());
	}


}
