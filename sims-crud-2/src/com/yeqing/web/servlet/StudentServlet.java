package com.yeqing.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yeqing.sims.dao.IStudentDAO;
import com.yeqing.sims.dao.impl.StudentDAOImpl;
import com.yeqing.sims.domain.Student;

//分发业务请求给相应的方法进行处理
@WebServlet("/student")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IStudentDAO dao;

	public void init() throws ServletException {
		dao = new StudentDAOImpl();
	}
    // 分发请求
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");  // 如果提交的信息中包含中文，接收所有参数之前，先设置字符编码
		// 1:获取请求参数
		String cmd = req.getParameter("cmd");
		// 2:将请求分发给不同的方法处理
		if ("save".equals(cmd)) {
			saveOrUpdate(req, resp);
		} else if ("delete".equals(cmd)) {
			delete(req, resp);
		} else if ("edit".equals(cmd)) {
			edit(req, resp);
		} else {
			query(req, resp);
		}
	};
    // 添加或修改学生信息
	protected void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1:接受请求参数，封装成对象
		String name = req.getParameter("name");
		Integer age = Integer.valueOf(req.getParameter("age"));
		Student s = new Student(name, age);
		String id = req.getParameter("id");
		// 2:调用业务方法处理请求
		if (hasLength(id)) { // 如果有id，则更新信息
			s.setId(Long.valueOf(id));
			dao.update(s);
		} else { // 如果无id，则保存信息
			dao.save(s);
		}
		// 3:控制页面跳转
		resp.sendRedirect("/student"); // 使用重定向，原因有二：其一，不用传递数据；其二，避免表单的重复提交
	};
	//删除指定学生信息
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1:接受请求参数，封装成对象
		Long id = Long.valueOf(req.getParameter("id"));
		// 2:调用业务方法处理请求
		dao.delete(id);
		// 3:控制页面跳转
		resp.sendRedirect("/student"); // 因为不需要传递参数，所有使用重定向即可
	};
	//查询所有学生的信息
	protected void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Student> list = dao.listAll();
		req.setAttribute("students", list);
		req.getRequestDispatcher("/WEB-INF/views/student/list.jsp").forward(req, resp);
	};
	//编辑学生信息
	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1:接受请求参数，封装成对象
		String id = req.getParameter("id");
		// 2:调用业务方法处理请求
		if (hasLength(id)) { // 判断是否存在请求参数id，如果有则查询要修改的学生信息，并设置到request作用域中
			req.setAttribute("s", dao.get(Long.valueOf(id)));
		}
		// 3:控制页面跳转
		req.getRequestDispatcher("/WEB-INF/views/student/edit.jsp").forward(req, resp);
	};

	private boolean hasLength(String str) {
		return str != null && !"".equals(str);
	}
}
