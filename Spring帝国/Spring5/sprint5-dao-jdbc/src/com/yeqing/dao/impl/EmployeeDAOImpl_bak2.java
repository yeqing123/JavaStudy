package com.yeqing.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.yeqing.dao.IEmployeeDAO;
import com.yeqing.domain.Employee;

@Repository
public class EmployeeDAOImpl_bak2 extends JdbcDaoSupport implements IEmployeeDAO {
	
	public void save(Employee emp) {
		super.getJdbcTemplate().update("INSERT INTO employee (name, age) VALUES (?,?)", emp.getName(), emp.getAge());
	}

	public void update(Employee emp) {
		super.getJdbcTemplate().update("UPDATE employee SET name = ?, age = ? WHERE id = ?", emp.getName(), emp.getAge(), emp.getId());
	}

	public void delete(Long id) {
		super.getJdbcTemplate().update("DELETE FROM employee WHERE id = ?", id);
	}
	@Override
	public Employee get(Long id) {
		/*这里的“(rs, rowNum) ->”使用的是Java8新增的Lanmda表达式。原本应该是这样写的：
		  jdbcTemplate.query("SELECT id,name,age FROM employee WHERE id = ?", new RowMapper<Employee>(){
		 	return Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee e = new Employee();
				e.setName(rs.getString("name"));
				e.setAge(rs.getInt("age"));
				e.setId(rs.getLong("id"));
				return e;
			}, id);
		 */
		List<Employee> list = super.getJdbcTemplate().query("SELECT id,name,age FROM employee WHERE id = ?", (rs, rowNum) -> {
				Employee e = new Employee();
				e.setName(rs.getString("name"));
				e.setAge(rs.getInt("age"));
				e.setId(rs.getLong("id"));
				return e;
			}, id);
		return list.size()==1?list.get(0):null;
	}   
		
	public List<Employee> listAll() {
		return super.getJdbcTemplate().query("SELECT id,name,age FROM employee", new RowMapper<Employee>() {
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee e = new Employee();
				e.setName(rs.getString("name"));
				e.setAge(rs.getInt("age"));
				e.setId(rs.getLong("id"));
				return e;
			}
		});
	}
}
