package com.yeqing.smis.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yeqing.smis.dao.IStudentDAO;
import com.yeqing.smis.dao.impl.StudentDAOImpl;
import com.yeqing.smis.domain.Student;

//处理查询所有学生信息的请求
@WebServlet("/student/list")
public class ListStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IStudentDAO dao;

    public void init() throws ServletException {
    	dao = new StudentDAOImpl();
    }
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
		//1:接受请求参数，封装成对象
		//2:调用业务方法处理请求
		List<Student> list = dao.listAll();
		req.setAttribute("students", list);
		//3:控制页面跳转
		req.getRequestDispatcher("/WEB-INF/views/student/student_list.jsp").forward(req, resp);
    }
}
