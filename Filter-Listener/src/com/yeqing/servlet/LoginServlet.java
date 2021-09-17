package com.yeqing.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yeqing.domain.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    	//记住servlet只做三件事：
    	//1:获取请求参数
    	String name = req.getParameter("name");
    	String password = req.getParameter("password");
    	User user = new User(name, password);
    	req.getSession().setAttribute("USER_IN_SESSION", user);
    	//2:调用业务方法，处理请求
    	//3:控制页面跳转
    	resp.sendRedirect("/welcome.jsp");
    }
}
