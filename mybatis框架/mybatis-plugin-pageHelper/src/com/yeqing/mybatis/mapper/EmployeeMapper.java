package com.yeqing.mybatis.mapper;

import java.util.List;

import com.yeqing.mybatis.domain.Employee;
import com.yeqing.mybatis.query.QueryObject;

public interface EmployeeMapper {

	List<Employee> advanceQuery(QueryObject qo);

	List<Employee> listAll();
}
