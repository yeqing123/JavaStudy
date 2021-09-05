package com.yeqing.eims.web.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yeqing.eims.dao.IEmployeeDAO;
import com.yeqing.eims.dao.impl.EmployeeDAOImpl;
import com.yeqing.eims.domain.Employee;

//处理用户的各项操作（增删改查）请求
@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IEmployeeDAO dao;
	public void init() throws ServletException {
		this.dao = new EmployeeDAOImpl();
	}
    protected void service(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");  // 如果请求参数中包含中文，必须在获取之前设置字符编码
    	String cmd = req.getParameter("cmd");
    	// 根据命令（cmd）内容分发请求
    	if("edit".equals(cmd)) {   //编辑
    		this.edit(req, resp);
    	} else if("save".equals(cmd))  {  //添加 
    		this.saveOrUpdate(req, resp);
    	} else if("update".equals(cmd)) {  //修改 
    		this.saveOrUpdate(req, resp); 
    	} else if("delete".equals(cmd)){  //删除
    		this.delete(req, resp);
    	} else {   //查询
    		this.query(req, resp);
    	}
    }
    protected void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	//1:获取请求参数，封装成对象
    	String name = req.getParameter("name");
    	String job = req.getParameter("job");
    	BigDecimal salary = new BigDecimal(req.getParameter("salary"));
    	Employee e = new Employee();
    	e.setName(name);
    	e.setJob(job);
    	e.setSalary(salary);
    	//2:调用业务方法，处理请求
    	String id = req.getParameter("id");  //获取edit.jsp页面中的隐藏标签值
    	if(hasLength(id)) {  //有id，修改学生信息
    		e.setId(Long.valueOf(id));
    		dao.update(e);
    	}else {              //无id，添加学生信息
    		dao.save(e);
    	}
    	//3:控制页面跳转
    	resp.sendRedirect("/employee");
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	//1:获取请求参数，封装成对象
    	String id = req.getParameter("id");
    	//2:调用业务方法，处理请求
    	dao.delete(Long.valueOf(id));
    	//3:控制页面跳转
    	resp.sendRedirect("/employee");
    }
    protected void query(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	//1:获取请求参数，封装成对象
    	//2:调用业务方法，处理请求
    	List<Employee> list = dao.getAll();
    	req.setAttribute("employees", list);
    	//3:控制页面跳转
    	req.getRequestDispatcher("/WEB-INF/employee/employee_list.jsp").forward(req, resp);
    }
    protected void edit(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	//1:获取请求参数，封装成对象
    	String id = req.getParameter("id");
    	//2:调用业务方法，处理请求
    	if(hasLength(id)) {
    		req.setAttribute("e", dao.get(Long.valueOf(id)));
    	}
    	//3:控制页面跳转
    	req.getRequestDispatcher("/WEB-INF/employee/edit.jsp").forward(req, resp);
    }
    private boolean hasLength(String str) {
    	return str != null && !"".equals(str);
    }
}
