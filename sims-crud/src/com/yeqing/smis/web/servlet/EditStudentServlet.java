package com.yeqing.smis.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yeqing.smis.dao.IStudentDAO;
import com.yeqing.smis.dao.impl.StudentDAOImpl;

//用于间接跳转到编辑学生信息的页面（edit.jsp）
@WebServlet("/student/edit")
public class EditStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IStudentDAO dao;

	public void init() throws ServletException {
		dao = new StudentDAOImpl();
	}
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1:接受请求参数，封装成对象
		String id = req.getParameter("id");
		// 2:调用业务方法处理请求
		if(hasLength(id)) {
			req.setAttribute("student", dao.get(Long.valueOf(id)));
		}
		// 3:控制页面跳转
		req.getRequestDispatcher("/WEB-INF/views/student/edit.jsp").forward(req, resp);
	}
    private boolean hasLength(String str) {
    	return str != null && !"".equals(str);
    }
}
