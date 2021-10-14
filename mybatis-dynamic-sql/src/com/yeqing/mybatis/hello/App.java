package com.yeqing.mybatis.hello;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yeqing.mybatis.hello.domain.Employee;
import com.yeqing.mybatis.hello.mapper.EmployeeMapper;
import com.yeqing.mybatis.hello.util.MybatisUtil;

public class App {
	
	@Test//查询工资高于或等于1000的员工
	public void testQuery1() throws Exception {
		SqlSession session = null;
		try {
			session = MybatisUtil.getSession();
			EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
			List<Employee> list = employeeMapper.query1(new BigDecimal(1000));
			for (Employee e : list) {
				System.out.println(e);
			}
		}finally {
			session.close();
		}
	}
	@Test
	public void testQuery2() throws Exception {
		SqlSession session = null;
		try {
			session = MybatisUtil.getSession();
			EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
			List<Employee> list = employeeMapper.query2(new BigDecimal(1000), new BigDecimal(2000));
			for (Employee e : list) {
				System.out.println(e);
			}
		}finally {
			session.close();
		}
	}
}
