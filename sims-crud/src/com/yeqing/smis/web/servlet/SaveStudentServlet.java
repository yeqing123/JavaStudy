package com.yeqing.smis.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yeqing.smis.dao.IStudentDAO;
import com.yeqing.smis.dao.impl.StudentDAOImpl;
import com.yeqing.smis.domain.Student;

//处理新增学生信息的请求
@WebServlet("/student/save")
public class SaveStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IStudentDAO dao;

	public void init() throws ServletException {
		dao = new StudentDAOImpl();
	}

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); // 注意：不要忘了设置请求的字符编码，该设置仅对POST请求有效
		// 1:接受请求参数，封装成对象
		String name = req.getParameter("name");
		Integer age = Integer.valueOf(req.getParameter("age"));
		Student s = new Student(name, age);
		// 2:调用业务方法处理请求
		String id = req.getParameter("id");
        if(hasLength(id)) {// 有id，修改
        	dao.update(Long.valueOf(id), s);
        } else {           // 无id，新增
		    dao.save(s);
        }
		// 3:控制页面跳转
		resp.sendRedirect("/student/list");
	}

	private boolean hasLength(String str) {
		return str != null && !"".equals(str);
	}
}
