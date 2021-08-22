package com.yeqing._03_inherit;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyHttpServlet extends MyGenericServlet {

	private static final long serialVersionUID = 1L;
	public void service(ServletRequest req, ServletResponse res) {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpRes = (HttpServletResponse) res;
        service(httpReq, httpRes);
	}
    public void service(HttpServletRequest req, HttpServletResponse res) {
    	System.out.println("MyHttpServlet.service()");
    	String method = req.getMethod();
    	if("GET".equals(method)) {
    		doGet(req, res);
    	} else if("POST".equals(method)) {
    		doPost(req, res);
    	}
    }
    private void doGet(HttpServletRequest req, HttpServletResponse res) {
    	System.out.println("MyHttpServlet.doGet()");
    }
    private void doPost(HttpServletRequest req, HttpServletResponse res) {
    	System.out.println("MyHttpServlet.doPost()");
    }
}
