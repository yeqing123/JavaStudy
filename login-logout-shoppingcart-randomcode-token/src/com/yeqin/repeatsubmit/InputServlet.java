package com.yeqin.repeatsubmit;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//用于跳转到input.jsp页面的请求
@WebServlet("/input")
public class InputServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//生成一个随机数（口令）
		String token = UUID.randomUUID().toString();
		//将口令设置到session中
		req.getSession().setAttribute("TOKEN_IN_SESSION", token);
		//将口令设置到request中，因为input.jsp中的也需要一份
		req.setAttribute("token", token);
		//将页面跳转到input.jsp
		req.getRequestDispatcher("/repeatsubmit/input.jsp").forward(req, resp);
	}
}
