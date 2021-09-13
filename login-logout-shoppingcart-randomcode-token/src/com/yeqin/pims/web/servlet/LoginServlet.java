package com.yeqin.pims.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yeqin.pims.dao.IUserDAO;
import com.yeqin.pims.dao.impl.UserDAOImpl;
import com.yeqin.pims.domain.User;

//用户处理用户登录请求
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    private IUserDAO dao;
    public void init() throws ServletException {
    	dao = new UserDAOImpl();
    }
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//1:获取请求参数
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		//2:调用业务方法，处理请求
		User user = dao.checkLogin(username);
		//3:控制页面跳转
		if(user == null) {
			req.setAttribute("errorMsg", "亲，您输入的账户不存在或已被禁用，请联系管理员！");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		    return;
		}
		if(!password.equals(user.getPassword())) {
			req.setAttribute("errorMsg", "亲，该账户或密码不正确！");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		    return;
		}
		req.getSession().setAttribute("USER_IN_SESSION", user);
		resp.sendRedirect("/product");
	}
}
