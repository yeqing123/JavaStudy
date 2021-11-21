package com.yeqing.dao.impl;


import org.springframework.stereotype.Repository;

import com.yeqing.dao.IEmployeeDAO;
import com.yeqing.domain.Employee;

@Repository
public class EmployeeDAOImpl implements IEmployeeDAO {

	public void save(Employee emp) {
		System.out.println("保存员工信息");
	}

	@Override
	public void update(Employee emp) {
		System.out.println("修改员工信息");
	}

}
