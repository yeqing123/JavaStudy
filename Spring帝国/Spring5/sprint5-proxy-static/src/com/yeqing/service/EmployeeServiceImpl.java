package com.yeqing.service;

import com.yeqing.dao.IEmployeeDAO;
import com.yeqing.domain.Employee;

public class EmployeeServiceImpl implements IEmployeeService {

	private IEmployeeDAO dao;
	
	public void setDao(IEmployeeDAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(Employee emp) {
		dao.save(emp);
		System.out.println("完成保存操作");
	}

	@Override
	public void update(Employee emp) {
		dao.update(emp);
		throw new RuntimeException("故意出错");
	}

}
