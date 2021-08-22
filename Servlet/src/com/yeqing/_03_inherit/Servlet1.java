package com.yeqing._03_inherit;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Servlet1 extends MyGenericServlet {

	private static final long serialVersionUID = 1L;
    
	public void init() {
		System.out.println("执行子类的初始化操作。。。");
	}
	public void service(ServletRequest req, ServletResponse res) {
		System.out.println("Servlet1.service()");
		String encoding = super.getInitParameter("encoding");
		System.out.println(encoding);
	}

}
