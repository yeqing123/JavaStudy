package com.yeqing.mybatis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yeqing.mybatis.domain.Employee;
import com.yeqing.mybatis.mapper.EmployeeMapper;
import com.yeqing.mybatis.util.MybatisUtil;

public class App {
	@Test//查询工资高于或等于1000的员工
	public void testQuery1() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
		List<Employee> emps = employeeMapper.query1(new BigDecimal(1000));
		for (Employee e : emps) {
			System.out.println(e);
		}
		session.close();
	}
	@Test//查询工资在1000~2000之间的员工
	public void testQuery2() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
		List<Employee> emps = employeeMapper.query2(new BigDecimal(1000), new BigDecimal(2000));
		for (Employee e : emps) {
			System.out.println(e);
		}
		session.close();
	}
	@Test//查询指定部门的员工
	public void testQuery3() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
		List<Employee> emps = employeeMapper.query3(new Long[] {10L,20L});
		for (Employee e : emps) {
			System.out.println(e);
		}
		session.close();
	}
	@Test//修改指定员工的信息
	public void testUpdate() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
		Employee e = new Employee();
		e.setName("BBB");
		e.setSn("111");
		e.setSalary(new BigDecimal(1000));
		e.setDeptId(30L);
		e.setId(25L);
		employeeMapper.update(e);
		session.commit();
		session.close();
	}
	@Test//批量插入数据
	public void testBatchSave() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
		Employee e1 = new Employee();
		Employee e2 = new Employee();
		e1.setName("BBB");
		e1.setSn("111");
		e1.setSalary(new BigDecimal(1000));
		e1.setDeptId(30L);
		e2.setName("CCC");
		e2.setSn("222");
		e2.setSalary(new BigDecimal(2000));
		e2.setDeptId(10L);
		List<Employee> emps = new ArrayList<>();
		emps.add(e1);
		emps.add(e2);
		employeeMapper.batchSave(emps);
		session.commit();
		session.close();
	}
	@Test//批量删除数据
	public void testBatchDelete() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
		employeeMapper.batchDelete(new Long[] {25L,26L,27L});
		session.commit();
		session.close();
	}
	
}
