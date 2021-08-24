package com.yeqing._05_sesseion;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/session/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	resp.setContentType("text/html;charset=utf-8");
    	PrintWriter out = resp.getWriter();
    	String name = req.getParameter("username");
    	
    	HttpSession session = req.getSession();  // 获取请求中的Session对象，如果没有就创建一个
    	session.setAttribute("USER_IN_SESSION", name);  // 将用户名设置到session对象中
    	// 如果浏览器禁用的Cookie，可以手动在地址栏添加;jsessionid，因为其实是Session也是一个特殊的Cookie
    	// 注意：资源名后面跟的是一个分号，而不是问号
    	String id = session.getId();
    	out.print("<h2>欢迎 "+name+"</h2>");
    	// 也可以使用resp.encodeURL()方法对地址url进行编码，增加session的id
    	String url = resp.encodeURL("/session/list");
    	System.out.println(url);
    	out.print("<a href='"+url+"'>收件箱</a>");
    }
 
}
