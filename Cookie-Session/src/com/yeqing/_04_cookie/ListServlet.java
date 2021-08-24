package com.yeqing._04_cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/cookie/list")
public class ListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		// ----------------------------------------------
		String name = "";
		//1.从请求中获取所有的Cookie对象
		Cookie[] cs = req.getCookies();
		//2.逐一判断每个Cookie中保存的内容是否为我们想要的参数，如果找到了就把它的值取出来
		//（为了正常显示中文，需要进行解码操作，如果英文则解码不影响显示）
		if(cs != null) {
			for (Cookie cookie : cs) {
				if("username".equals(cookie.getName())) {
					name = URLDecoder.decode(cookie.getValue(), "UTF-8");
					//修改Cookie的内容有两种方式： 
					//1.Cookie对象.setValue方法;
					//cookie.setValue("西门吹雪");
					//2.重新创建一个Cookie对象；
					//Cookie c = new Cookie("username", "乔峰");
					// 注意：两种方式都要重新加入到响应中才能生效
					//resp.addCookie(c);
				}
			}
		}
		out.print("<h2>欢迎 "+name+"</h2>");
		out.print("<a href='/cookie/get'>一封邮件</a><br/>");
		out.print("<a href='/cookie/get'>一封邮件</a><br/>");
		out.print("<a href='/cookie/get'>一封邮件</a><br/>");
		out.print("<a href='/cookie/get'>一封邮件</a><br/>");
    }
} 
