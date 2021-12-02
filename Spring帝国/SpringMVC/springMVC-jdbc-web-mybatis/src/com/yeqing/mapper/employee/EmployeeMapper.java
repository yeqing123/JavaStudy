package com.yeqing.mapper.employee;

import java.util.List;

import com.yeqing.domain.Employee;

//规定了具体操作的接口
public interface EmployeeMapper {

	void save(Employee e);

	Employee get(Long id);

	void delete(Long id);

	void update(Employee e);

	List<Employee> listAll();

}
