package com.yeqing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeqing.domain.Employee;
import com.yeqing.mybatis.dao.impl.EmployeeDAOImpl;
import com.yeqing.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	private EmployeeDAOImpl dao;

	public void save(Employee e) {
		dao.save(e);
	}
	public void delete(Long id) {
		dao.delete(id);
	}
	public void update(Employee e) {
		dao.update(e);
	}
	public Employee get(Long id) {
		return dao.get(id);
	}
	public List<Employee> listAll() {
		return dao.listAll();
	}
}
