package com.yeqing._02_redirect;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/redirect/s1")
public class Servlet1 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
	    resp.setContentType("text/html;charset=utf-8");
	    PrintWriter out = resp.getWriter();
		System.out.println("Servlet1.....before " + req.getParameter("name"));
	    out.println("Servlet1...");
	    //==============================================
	    // URL重定向
	    resp.sendRedirect("/WEB-INF/login.html");
	    //==============================================
	    System.out.println("Servlet1.....after");
	}
}
