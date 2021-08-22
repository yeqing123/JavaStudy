package com.yeqing._01_hello;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloServlet implements Servlet {
    public HelloServlet() {
    	System.out.println("构造HelloServlelt");
    }
	public void init(ServletConfig config) throws ServletException {
		System.out.println("初始化...");
	}

	public ServletConfig getServletConfig() {
		return null;
	}

	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("服务...");
	}

	public String getServletInfo() {
		return null;
	}

	public void destroy() {
		System.out.println("销毁...");
	}

}
