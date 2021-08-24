package com.yeqing._04_cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/cookie/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		// ----------------------------------------------
		String name = req.getParameter("username");
		//1.创建一个Cookie对象，并设置其内容(因为Cookie不支持中文，所以先进行编码，再存入Cookie)
		String str = URLEncoder.encode(name, "UTF-8");
		Cookie cookie = new Cookie("username", str);
		cookie.setMaxAge(30);
		//2.将该Cookie对象加入到响应中
		resp.addCookie(cookie);
		out.print("<h2>欢迎"+name+"驾到！</h2>");
		out.print("<a href='/cookie/list'>收件箱</a>");
	}
}
