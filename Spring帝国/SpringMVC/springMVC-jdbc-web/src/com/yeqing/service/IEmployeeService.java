package com.yeqing.service;

import java.util.List;

import com.yeqing.domain.Employee;

public interface IEmployeeService {
	
	public void save(Employee e);
	
	public void delete(Long id);
	
	public void update(Employee e);
	
	public Employee get(Long id);
	
	public List<Employee> listAll();
}
