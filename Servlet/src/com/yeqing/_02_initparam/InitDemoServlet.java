package com.yeqing._02_initparam;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class InitDemoServlet implements Servlet {
    
	private ServletConfig config;
	public void init(ServletConfig config) throws ServletException {
	    this.config = config;
	}

	public ServletConfig getServletConfig() {
		return null;
	}

	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// 不能将代码写死，这样今后如果拿到的只是字节码文件就无法进行修改，
		// 解决办法是在web.xml文件中配置<init-param>属性，今后只需要修改文件即可
//		String encoding = "UTF-8";
//		if("GBK".equals(encoding)) {
//			System.out.println("你好世界");
//		} else {
//			System.out.println("Hello world");
//		}
		String servlet = config.getServletName();
		System.out.println(servlet);
		String encoding = config.getInitParameter("encoding");
		if("UTF-8".equals(encoding)) {
			System.out.println("你好世界");
		} else {
			System.out.println("Hello world");
		}
		Enumeration<String> paramNames = config.getInitParameterNames();
		while(paramNames.hasMoreElements()) {
			System.out.println(paramNames.nextElement());
		}
	}

	public String getServletInfo() {
		return null;
	}

	public void destroy() {
		
	}
    
}
