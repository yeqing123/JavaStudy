package com.yeqing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeqing.dao.IEmployeeDAO;
import com.yeqing.domain.Employee;
import com.yeqing.service.IEmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	private IEmployeeDAO dao;

	public void save(Employee e) {
		dao.save(e);
	}
	public void delete(Long id) {
		dao.delete(id);
	}
	public void update(Employee e) {
		dao.update(e);
	}
	@Transactional(readOnly = true)
	public Employee get(Long id) {
		return dao.get(id);
	}
	@Transactional(readOnly = true)
	public List<Employee> listAll() {
		return dao.listAll();
	}
}
