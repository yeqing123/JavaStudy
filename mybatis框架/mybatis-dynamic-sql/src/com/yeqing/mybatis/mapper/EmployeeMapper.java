package com.yeqing.mybatis.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yeqing.mybatis.domain.Employee;

public interface EmployeeMapper {

	List<Employee> query1(@Param("minsalary")BigDecimal bigDecimal);

	List<Employee> query2(@Param("minsalary")BigDecimal bigDecimal, @Param("maxsalary")BigDecimal bigDecimal2);

	List<Employee> query3(@Param("deptIds")Long[] longs);

	void update(@Param("e")Employee e);

	void batchSave(@Param("emps")List<Employee> emps);

	void batchDelete(@Param("ids")Long[] longs);

}
