package com.yeqing.service;

import com.yeqing.dao.IEmployeeDAO;

public class EmployeeService {
	private IEmployeeDAO dao;
	public void setDao(IEmployeeDAO dao) {
		this.dao = dao;
	}
}
