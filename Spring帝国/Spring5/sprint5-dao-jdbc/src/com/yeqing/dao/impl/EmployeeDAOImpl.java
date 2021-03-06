package com.yeqing.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yeqing.dao.IEmployeeDAO;
import com.yeqing.domain.Employee;

@Repository
@SuppressWarnings("unchecked")
public class EmployeeDAOImpl implements IEmployeeDAO {

	private NamedParameterJdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource ds) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(ds);
	}

	public void save(Employee emp) {
		jdbcTemplate.update("INSERT INTO employee (name, age) VALUES (:ename,:eage)", new HashMap() {
			{
				this.put("ename", emp.getName());
				this.put("eage", emp.getAge());
			}
		});
	}

	public void update(Employee emp) {
		jdbcTemplate.update("UPDATE employee SET name = :ename, age = :eage WHERE id = :eid", new HashMap() {
			{
				this.put("ename", emp.getName());
				this.put("eage", emp.getAge());
				this.put("eid", emp.getId());
			}
		});
	}

	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM employee WHERE id = :eid", new HashMap() {
			{
				this.put("eid", id);
			}
		});
	}

	@Override
	public Employee get(Long id) {
        //不推荐使用queryForObject方法，因为如果查询不到数据会抛出一个异常。
		//推荐使用query方法，如果查询不到就返回空。
		return jdbcTemplate.queryForObject("SELECT id,name,age FROM employee WHERE id = :eid", new HashMap() {
			{
				this.put("eid", id);
			}
		}, new RowMapper<Employee>() {
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee e = new Employee();
				e.setName(rs.getString("name"));
				e.setAge(rs.getInt("age"));
				e.setId(rs.getLong("id"));
				return e;
			}
		});
	}

	public List<Employee> listAll() {
		return jdbcTemplate.query("SELECT id,name,age FROM employee", new RowMapper<Employee>() {
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
