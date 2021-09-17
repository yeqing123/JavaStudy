package com.yeqing.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yeqing.domain.User;

//获取并封装请求参数，在request作用域中设置属性
@WebServlet("/show")
public class ShowServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    	String name = req.getParameter("name");
    	String password = req.getParameter("password");
    	User user = new User();
    	user.setName(name);
    	user.setPassword(password);
    	req.setAttribute("USER_IN_REQUEST", user);
    	req.getRequestDispatcher("/show.jsp").forward(req, resp);
    }
}
