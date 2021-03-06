package com.yeqing.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yeqing.dao.IEmployeeDAO;
import com.yeqing.domain.Employee;
import com.yeqing.mapper.employee.EmployeeMapper;
import com.yeqing.util.MybatisUtil;

@Repository
public class EmployeeDAOImpl implements IEmployeeDAO {
	
	private EmployeeMapper mapper = MybatisUtil.getMapper(EmployeeMapper.class);
	public void save(Employee e) {
		mapper.save(e);
		int a = 1/0;
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
