package com.yeqing.smis.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yeqing.smis.dao.IStudentDAO;
import com.yeqing.smis.dao.impl.StudentDAOImpl;

//处理删除指定的学生信息的请求
@WebServlet("/student/delete")
public class DeleteStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IStudentDAO dao;

	public void init() throws ServletException {
		dao = new StudentDAOImpl();
	}
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1:接受请求参数，封装成对象
		Long id = Long.valueOf(req.getParameter("id"));
		// 2:调用业务方法处理请求
		dao.delete(id);
		// 3:控制页面跳转
		//因为不用传递数据，所有用重定向
		resp.sendRedirect("/student/list");
	}
}
