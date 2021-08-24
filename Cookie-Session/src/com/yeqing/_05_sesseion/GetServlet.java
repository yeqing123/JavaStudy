package com.yeqing._05_sesseion;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/session/get")
public class GetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	resp.setContentType("text/html;charset=utf-8");
    	PrintWriter out = resp.getWriter();
    	//------------------------------------------
    	HttpSession session = req.getSession();
    	session.setMaxInactiveInterval(15);  // 设置Session的超时管理为15秒（15秒内无任何操作则自动销毁Session对象）
    	String name = (String)session.getAttribute("USER_IN_SESSION");
    	out.print("<h2>欢迎"+name+"</h2>");
    	out.print("叶哥，我想请你吃饭！");
    }
}
