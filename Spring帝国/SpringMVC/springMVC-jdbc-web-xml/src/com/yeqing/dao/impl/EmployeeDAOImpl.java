package com.yeqing.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yeqing.dao.IEmployeeDAO;
import com.yeqing.dao.mapper.EmployeeMapper;
import com.yeqing.domain.Employee;
import com.yeqing.util.MybatisUtil;

public class EmployeeDAOImpl implements IEmployeeDAO {
	
	
	public void save(Employee e) {
		SqlSession session = MybatisUtil.getSqlSession();
		session.insert("com.yeqing.dao.mapper.EmployeeMapper.save", e);
		session.commit();
		session.close();
	}

	public void delete(Long id) {
		SqlSession session = MybatisUtil.getSqlSession();
		session.delete("com.yeqing.dao.mapper.EmployeeMapper.delete", id);
		session.commit();
		session.close();
	}

	public void update(Employee e) {
		SqlSession session = MybatisUtil.getSqlSession();
		session.update("com.yeqing.dao.mapper.EmployeeMapper.update", e);
		session.commit();
		session.close();
	}

	public Employee get(Long id) {
		SqlSession session = MybatisUtil.getSqlSession();
		Employee e = session.selectOne("com.yeqing.dao.mapper.EmployeeMapper.get", id);
		session.close();
		return e;
	}

	public List<Employee> listAll() {
		SqlSession session = MybatisUtil.getSqlSession();
		List<Employee> list = session.selectList("com.yeqing.dao.mapper.EmployeeMapper.listAll");
		session.close();
		return list;
	}

}
