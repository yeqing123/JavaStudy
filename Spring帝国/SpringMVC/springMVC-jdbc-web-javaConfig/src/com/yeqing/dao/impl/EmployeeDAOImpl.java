package com.yeqing.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yeqing.dao.IEmployeeDAO;
import com.yeqing.domain.Employee;

@Repository
@Transactional
public class EmployeeDAOImpl implements IEmployeeDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}

	public void save(Employee e) {
		jdbcTemplate.update("INSERT INTO employee (username, age, hiredate) VALUES (?,?,?)", e.getUsername(),
				e.getAge(), e.getHiredate());
	}

	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM employee WHERE id = ?", id);
	}

	public void update(Employee e) {
		jdbcTemplate.update("UPDATE employee SET username=?, age=?, hiredate=? WHERE id = ?", e.getUsername(),
				e.getAge(), e.getHiredate(), e.getId());
	}

	public Employee get(Long id) {
		List<Employee> list = jdbcTemplate.query("SELECT id, username, age, hiredate FROM employee WHERE id = ?", new RowMapper<Employee>() {
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee e = new Employee();
				e.setId(rs.getLong("id"));
				e.setUsername(rs.getString("username"));
				e.setAge(rs.getInt("age"));
				e.setHiredate(rs.getDate("hiredate"));
				return e;
			}
		}, id);
		return list.size()==1?list.get(0):null;
	}

	public List<Employee> listAll() {
		return jdbcTemplate.query("SELECT id, username, age, hiredate FROM employee", new RowMapper<Employee>() {
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee e = new Employee();
				e.setId(rs.getLong("id"));
				e.setUsername(rs.getString("username"));
				e.setAge(rs.getInt("age"));
				e.setHiredate(rs.getDate("hiredate"));
				return e;
			}
		});
	}

}
