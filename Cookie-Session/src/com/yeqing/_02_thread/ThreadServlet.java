package com.yeqing._02_thread;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/th")
public class ThreadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// 因为在Servlet容器中每个Servlet对象都是单例的，所以如果使用成员变量，在多线程的情况下多个线程会共有同一个单例，
	// 后一个线程会覆盖掉前一个线程的成员变量的值，这是安全的。解决办法是不用成员变量，改用局部变量
    String username = "";
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	resp.setContentType("text/html;charset=utf-8");
    	username = req.getParameter("username");
    	// 让服务器在输出结果之前先睡3秒
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	System.out.println("name:" + username);
    }
}
