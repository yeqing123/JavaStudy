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

//处理学生信息的所有请求（增删改查）操作
@WebServlet("/student")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IStudentDAO dao;

	public void init() throws ServletException {
		dao = new StudentDAOImpl();
	}

	// service()方法用于分发请求：
	// http://localhost:8080/student            表示显示学生信息的列表
	// http://localhost:8080/student?cmd=save   表示新增或修改学生信息
	// http://localhost:8080/student?cmd=delete 表示删除学生信息
	// http://localhost:8080/student?cmd=edit   表示编辑学生信息
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 一定要在获取参数之前，设置参数的字符编码，否则设置将失效
		req.setCharacterEncoding("UTF-8");
		// 获取请求的操作类型
		String cmd = req.getParameter("cmd");
		if ("save".equals(cmd)) { // 根据操作类型分发请求
			this.saveOrUpdate(req, resp);
		} else if ("delete".equals(cmd)) {
			this.delete(req, resp);
		} else if ("edit".equals(cmd)) {
			this.edit(req, resp);
		} else {
			this.list(req, resp);
		}
	}

	// 处理查询所有学生信息的请求
	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1:接受请求参数，封装成对象
		// 2:调用业务方法处理请求
		List<Student> list = dao.listAll();
		req.setAttribute("students", list);
		// 3:控制页面跳转
		req.getRequestDispatcher("/WEB-INF/views/student/student_list.jsp").forward(req, resp);
	}

	// 处理删除指定的学生信息的请求
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1:接受请求参数，封装成对象
		Long id = Long.valueOf(req.getParameter("id"));
		// 2:调用业务方法处理请求
		dao.delete(id);
		// 3:控制页面跳转
		// 因为不用传递数据，所有用重定向
		resp.sendRedirect("/student");
	}

	// 用于间接跳转到编辑学生信息的页面（edit.jsp）
	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1:接受请求参数，封装成对象
		String id = req.getParameter("id");
		// 2:调用业务方法处理请求
		if (hasLength(id)) {
			req.setAttribute("student", dao.get(Long.valueOf(id)));
		}
		// 3:控制页面跳转
		req.getRequestDispatcher("/WEB-INF/views/student/edit.jsp").forward(req, resp);
	}

	// 处理新增学生信息的请求
	protected void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1:接受请求参数，封装成对象
		String name = req.getParameter("name");
		Integer age = Integer.valueOf(req.getParameter("age"));
		Student s = new Student(name, age);
		// 2:调用业务方法处理请求
		String id = req.getParameter("id");
		if (hasLength(id)) {// 有id，修改
			dao.update(Long.valueOf(id), s);
		} else { // 无id，新增
			dao.save(s);
		}
		// 3:控制页面跳转
		resp.sendRedirect("/student");
	}

	private boolean hasLength(String str) {
		return str != null && !"".equals(str);
	}
}
