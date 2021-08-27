package com.yeqing._01_forward;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/forward/s2")
public class Servlet2 extends HttpServlet {

	private static final long serialVersionUID = 1L;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    	resp.setContentType("text/html;charset=utf-8");
	    PrintWriter out = resp.getWriter();
	    out.println("Servlet2..." + req.getParameter("name"));
    }
}
