package com.yeqing.eims.dao.impl;

import java.util.List;

import com.yeqing.eims.dao.IEmployeeDAO;
import com.yeqing.eims.domain.Employee;
import com.yeqing.eims.util.JdbcTemplate;
import com.yeqing.eims.util.handler.BeanHandler;
import com.yeqing.eims.util.handler.BeanListHandler;

public class EmployeeDAOImpl implements IEmployeeDAO {

	public void save(Employee emp) {
		String sql = "INSERT INTO t_employee (name, job, salary) VALUES (?,?,?)";
		JdbcTemplate.update(sql, emp.getName(), emp.getJob(), emp.getSalary());
	}

	public void delete(Long id) {
		String sql = "DELETE FROM t_employee WHERE e_id=?";
		JdbcTemplate.update(sql, id);
	}

	public void update(Employee emp) {
		String sql = "UPDATE t_employee SET name=?,job=?,salary=? WHERE e_id=?";
		JdbcTemplate.update(sql, emp.getName(), emp.getJob(), emp.getSalary(), emp.getId());
	}

	public Employee get(Long id) {
		String sql = "SELECT * FROM t_employee WHERE e_id=?";
		return JdbcTemplate.query(sql, new BeanHandler<Employee>(Employee.class), id);
	}

	public List<Employee> getAll() {
		String sql = "SELECT * FROM t_employee";
		return JdbcTemplate.query(sql, new BeanListHandler<Employee>(Employee.class));
	}

}
