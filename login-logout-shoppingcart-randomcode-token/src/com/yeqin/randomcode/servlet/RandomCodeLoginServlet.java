package com.yeqin.randomcode.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yeqin.pims.dao.IUserDAO;
import com.yeqin.pims.dao.impl.UserDAOImpl;
import com.yeqin.pims.domain.User;
import com.yeqin.randomcode.RandomCode;

//处理登录请求
@WebServlet("/randomcodeLogin")
public class RandomCodeLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IUserDAO dao;
	public void init() throws ServletException {
		dao = new UserDAOImpl();
	}
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    	//1:获取请求方法
    	String username = req.getParameter("username");
    	String password = req.getParameter("password");
    	String randomcode = req.getParameter("randomcode");
    	//先判断验证码，再进行登录验证
    	RandomCode randomcodeInSession = (RandomCode) req.getSession().getAttribute("RANDOMCODE_IN_SESSION");
    	if(randomcodeInSession.getText().equalsIgnoreCase(randomcode)) {
    		//2:调用业务方法，处理请求
    		User user = dao.checkLogin(username);
    		//3:控制页面跳转
    		if(user == null) {
    			req.setAttribute("errorMsg", "亲，该账户不存在或已被禁用，请联系管理员");
    			req.getRequestDispatcher("/randomcode/login.jsp").forward(req, resp);
    		} else if(!password.equals(user.getPassword())) {
    			req.setAttribute("errorMsg", "亲，你输入的账户或密码错误！");
    			req.getRequestDispatcher("/randomcode/login.jsp").forward(req, resp);
    		} else {
    			req.getSession().setAttribute("USER_IN_SESSION", user);
    			req.getRequestDispatcher("/product").forward(req, resp);
    		}
    	}else {
    		req.setAttribute("errorMsg", "亲，你输入验证码错误！");
			req.getRequestDispatcher("/randomcode/login.jsp").forward(req, resp);
    	}
    }
}
