package com.yeqing.mybatis.mapper;

import java.util.List;

import com.yeqing.mybatis.domain.Employee;

public interface EmployeeMapper {

	void save(Employee e);

	Employee get(Long id);

	List<Employee> anotherForListAll();

	List<Employee> listAll();

}
