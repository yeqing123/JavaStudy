package com.yeqing._03_param;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/param/list")
public class ListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		// ----------------------------------------------
		String name = req.getParameter("username");
		out.print("<h2>欢迎 "+name+"</h2>");
		out.print("<a href='/param/get?username="+name+"'>一封邮件</a><br/>");
		out.print("<a href='/param/get?username="+name+"'>一封邮件</a><br/>");
		out.print("<a href='/param/get?username="+name+"'>一封邮件</a><br/>");
		out.print("<a href='/param/get?username="+name+"'>一封邮件</a><br/>");
    }
} 
