package com.yeqing._05_sesseion;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/session/list")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void service(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	resp.setContentType("text/html;charset=utf-8");
    	PrintWriter out = resp.getWriter();
    	//------------------------------------------
    	HttpSession session = req.getSession();
    	session.setMaxInactiveInterval(15);  // 设置Session的超时管理为15秒（15秒内无任何操作则自动销毁Session对象）
    	String name = (String)session.getAttribute("USER_IN_SESSION");  // 因为Session中保存的是Object对象，所以取出来需要进行强转
    	// 如果浏览器禁用的Cookie，可以手动在地址栏添加session对象的jsessionid值，因为其实是Session也是一个特殊的Cookie
    	out.print("<h1>欢迎"+name+"</h2>");
    	out.print("<a href='/session/get'>一封邮件</a><br/>");
    	out.print("<a href='/session/get'>一封邮件</a><br/>");
    	out.print("<a href='/session/get'>一封邮件</a><br/>");
    	out.print("<a href='/session/get'>一封邮件</a><br/>");
    }
}
