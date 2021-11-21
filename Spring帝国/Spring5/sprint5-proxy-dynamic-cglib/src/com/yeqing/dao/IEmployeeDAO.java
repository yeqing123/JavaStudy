package com.yeqing.dao;

import com.yeqing.domain.Employee;

public interface IEmployeeDAO {

	void save(Employee emp);
	
	void update(Employee emp);
}
