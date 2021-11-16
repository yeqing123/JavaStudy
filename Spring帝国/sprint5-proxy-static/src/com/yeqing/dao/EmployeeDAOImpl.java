package com.yeqing.dao;


import com.yeqing.domain.Employee;

public class EmployeeDAOImpl implements IEmployeeDAO {

	public void save(Employee emp) {
		System.out.println("保存员工信息");
	}

	@Override
	public void update(Employee emp) {
		System.out.println("修改员工信息");
	}

}
