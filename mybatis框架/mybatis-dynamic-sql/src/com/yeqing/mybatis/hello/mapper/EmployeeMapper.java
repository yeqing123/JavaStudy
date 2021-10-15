package com.yeqing.mybatis.hello.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yeqing.mybatis.hello.domain.Employee;

public interface EmployeeMapper {

	List<Employee> query1(@Param("minSalary")BigDecimal minSalary);

	List<Employee> query2(@Param("minSalary")BigDecimal minSalary, @Param("maxSalary")BigDecimal maxSalary);

}
