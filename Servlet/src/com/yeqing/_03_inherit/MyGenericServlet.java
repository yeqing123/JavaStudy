package com.yeqing._03_inherit;

import java.io.Serializable;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

abstract public class MyGenericServlet implements Servlet, ServletConfig, Serializable {

	private static final long serialVersionUID = 1L;
	private ServletConfig config;

	public void init(ServletConfig config) throws ServletException {
		this.config = config;
		this.init();
	}

	public ServletConfig getServletConfig() {
		return this.config;
	}

	abstract public void service(ServletRequest req, ServletResponse res);

	public String getServletInfo() {
		return null;
	}

	public void destroy() {

	}
	
	public void init() {
		// NOOP
	}
    
    public String getServletName() {
    	return getServletConfig().getServletName();
    }

    public ServletContext getServletContext() {
    	return getServletConfig().getServletContext();
    }
   
    public String getInitParameter(String name) {
    	return getServletConfig().getInitParameter(name);
    }

    public Enumeration<String> getInitParameterNames() {
    	return getServletConfig().getInitParameterNames();
    }
}
