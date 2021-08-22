package com.yeqing._03_inherit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet2 extends MyHttpServlet {

	private static final long serialVersionUID = 1L;
    public void init() {
    	System.out.println("Servlet2的初始化操作");
    }
    public void service(HttpServletRequest req, HttpServletResponse res) {
    	System.out.println("Servlet2.service()");
    }
}
