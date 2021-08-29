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




@WebServlet("/student/list")
//永远记住Servlet的任务是：1、获取请求参数；2、调用业务处理方法，处理请求；3、//控制页面跳转
public class ListServlet extends HttpServlet {
	private IStudentDAO dao;
	private static final long serialVersionUID = 1L;
	public void init() throws ServletException {
        dao = new StudentDAOImpl();
	}
    protected void service(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	//调用业务处理方法，处理请求
    	List<Student> list = dao.listAll();
    	// 因为使用请求转发，只有一个请求，所以使用request作用域对象就够了
    	req.setAttribute("students", list);
    	//控制页面跳转
    	req.getRequestDispatcher("/WEB-INF/views/student/student_list.jsp").forward(req, resp);
    }
}
