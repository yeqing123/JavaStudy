package com.yeqing.exercise.dao.impl;

import java.util.List;

import com.yeqing.exercise.dao.IEmployeeDAO;
import com.yeqing.exercise.domain.Employee;
import com.yeqing.exercise.handler.BeanHandler;
import com.yeqing.exercise.handler.BeanListHandler;
import com.yeqing.exercise.util.JdbcTemplate;

// 用JDBC实现对数据库的操作，永远记住有5个步骤：贾琏欲执事！
public class EmployeeDAOImpl implements IEmployeeDAO {
	
	public void save(Employee emp) {
		String sql = "INSERT INTO t_employee (name,job,salary) VALUES (?,?,?)";
		JdbcTemplate.update(sql, emp.getName(), emp.getJob(), emp.getSalary());
	}

	public void delete(Long id) {
		String sql = "DELETE FROM t_employee WHERE e_id = ?";
		JdbcTemplate.update(sql, id);
	}

	public void update(Long id, Employee emp) {
		String sql = "UPDATE t_employee SET name=?, job=?, salary=? WHERE e_id=?";
		JdbcTemplate.update(sql, emp.getName(), emp.getJob(), emp.getSalary(), id);
	}

	public Employee get(Long id) {
		String sql = "SELECT * FROM t_employee WHERE e_id = ?";
		return JdbcTemplate.query(sql, new BeanHandler<Employee>(Employee.class), id);
		
	}

	public List<Employee> listAll() {
		String sql = "SELECT * FROM t_employee";
		return JdbcTemplate.query(sql, new BeanListHandler<Employee>(Employee.class));
	}

}
