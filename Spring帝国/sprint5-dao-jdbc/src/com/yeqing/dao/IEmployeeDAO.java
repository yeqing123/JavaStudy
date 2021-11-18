package com.yeqing.dao;

import java.util.List;

import com.yeqing.domain.Employee;

public interface IEmployeeDAO {

	void save(Employee emp);
	
	void update(Employee emp);
	
	void delete(Long id);
	
	Employee get(Long id);
	
	List<Employee> listAll();	
}
