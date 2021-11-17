package com.yeqing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeqing.dao.IEmployeeDAO;
import com.yeqing.domain.Employee;
import com.yeqing.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeDAO dao;
	
	public void save(Employee emp) {
		dao.save(emp);
		System.out.println("完成保存操作");
	}

	public void update(Employee emp) {
		dao.update(emp);
		throw new RuntimeException("故意出错");
	}

}
