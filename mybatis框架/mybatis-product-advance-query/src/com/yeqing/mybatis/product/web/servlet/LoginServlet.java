package com.yeqing.mybatis.product.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yeqing.mybatis.product.dao.mapper.UserMapper;
import com.yeqing.mybatis.product.dao.util.MybatisUtil;
import com.yeqing.mybatis.product.domain.User;

@WebServlet("/check")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserMapper mapper = MybatisUtil.getMapper(UserMapper.class);
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//获取请求参数，封装成对象
    	String username = req.getParameter("username");
    	String password = req.getParameter("password");
    	//调用过业务方法处理请求
    	boolean isLogin = false;   //是否可以登录的标记符，默认为false
    	if(username != null) {
    		User u = mapper.get(username);
    		if(u != null) {
    			System.out.println(u);
    			if(u.getPassword().equals(password)) {   //判断密码是否正确
    				isLogin = true;                      //正确，将标记符改为true
    				req.getSession().setAttribute("u", u);  //登录成功，就在Session对象中保存用户信息
    			} else {                                 //不正确，设置提示信息
    				req.setAttribute("msg", "您输入的密码不正确！");
    				req.setAttribute("username", username);
    			}
    		}else {
    			req.setAttribute("msg", "您输入的用户名不存在！");
    		}
    	}
    	//控制页面跳转
    	if(isLogin) {
    		resp.sendRedirect("/products?cmd=list");
    	}else {
    		req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    	}
    }
}
