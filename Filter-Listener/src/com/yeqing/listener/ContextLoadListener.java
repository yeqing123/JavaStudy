package com.yeqing.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//应用系统启动（加载）和关闭（销毁）监听器
@WebListener
public class ContextLoadListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
	//	System.out.println("Web系统启动了...");
	}

	public void contextDestroyed(ServletContextEvent sce) {
	//	System.out.println("Web系统关闭了...");
	}

}
