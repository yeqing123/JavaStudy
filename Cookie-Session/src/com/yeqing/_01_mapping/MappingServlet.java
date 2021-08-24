package com.yeqing._01_mapping;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns= {"/m2","/m1"},
    initParams={
    		@WebInitParam(name="username", value="yeqing"),
    		@WebInitParam(name="password", value="12345")
    	},loadOnStartup=-1)
public class MappingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    	System.out.println("MappingServlet.service()");
    }
    public void init() {
    	System.out.println("MappingServlet.init()");
    	String name = super.getInitParameter("username");
    	String password = super.getInitParameter("password");
    	System.out.println(name + "," + password);
    }
}
