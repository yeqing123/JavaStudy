package com.yeqing.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yeqing.domain.Speak;

//处理网站的发言请求
@WebServlet("/deliver")
public class ForumServlet extends HttpServlet {
    private List<Speak> list = new ArrayList<>();
	private static final long serialVersionUID = 1L;
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    	MyHttpServletRequestWrapper myReq = new MyHttpServletRequestWrapper(req);
    	//1:接收请求参数，封装成对象
    	String title = myReq.getParameter("title");
    	String content = myReq.getParameter("content");
    	Speak s = new Speak();
    	s.setTitle(title);
    	s.setContent(content);
    	list.add(s);
    	req.getSession().setAttribute("LIST_IN_SESSION", list);
    	//3:控制页面跳转
    	resp.sendRedirect("/forum.jsp");
    }
}
