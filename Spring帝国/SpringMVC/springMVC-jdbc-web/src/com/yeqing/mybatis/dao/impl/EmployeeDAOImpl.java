package com.yeqing.mybatis.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yeqing.domain.Employee;
import com.yeqing.mybatis.dao.IEmployeeDAO;
import com.yeqing.mybatis.mapper.EmployeeMapper;
import com.yeqing.util.MybatisUtil;

@Repository
public class EmployeeDAOImpl implements IEmployeeDAO {
	
	private EmployeeMapper mapper = MybatisUtil.getMapper(EmployeeMapper.class);
	public void save(Employee e) {
		mapper.save(e);
	}

	public void delete(Long id) {
		mapper.delete(id);
	}

	public void update(Employee e) {
		mapper.update(e);
	}

	public Employee get(Long id) {
		return mapper.get(id);
	}

	public List<Employee> listAll() {
		return mapper.listAll();
	}

}
