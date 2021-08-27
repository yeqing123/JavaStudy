package com.yeqing._03_scope;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    	resp.setContentType("text/html;charset=utf-8");
	    PrintWriter out = resp.getWriter();
	    out.println("request=" + req.getAttribute("NUM_IN_REQUEST") +"<br/>");
	    out.println("session=" + req.getSession().getAttribute("NUM_IN_SESSION") +"<br/>");
	    out.println("application=" +super.getServletContext().getAttribute("NUM_IN_APP") +"<br/>");
    }
}
