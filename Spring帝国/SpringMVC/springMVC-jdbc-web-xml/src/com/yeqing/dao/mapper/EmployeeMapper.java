package com.yeqing.dao.mapper;

import com.yeqing.domain.Employee;

public interface EmployeeMapper {

	void save(Employee e);

	Employee get(Long id);

}
