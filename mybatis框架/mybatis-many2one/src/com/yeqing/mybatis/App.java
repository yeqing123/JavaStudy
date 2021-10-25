package com.yeqing.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yeqing.mybatis.domain.Department;
import com.yeqing.mybatis.domain.Employee;
import com.yeqing.mybatis.mapper.DepartmentMapper;
import com.yeqing.mybatis.mapper.EmployeeMapper;
import com.yeqing.mybatis.util.MybatisUtil;

//测试添加新员工、额外SQL、N+1问题解决方法、多表查询和内联映射
public class App {
	
	@Test
	public void testSave() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
		DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
		//如果同时要新增部分和员工，必须先添加部门，再添加员工
		Department d = new Department();
		d.setName("测试部门");
		departmentMapper.save(d);
		//创建并新增两个员工
		Employee e1 = new Employee();
		e1.setName("王五");
		e1.setDept(d);
		Employee e2 = new Employee();
		e2.setName("赵六");
		e2.setDept(d);
		employeeMapper.save(e1);
		employeeMapper.save(e2);
		session.commit();
		session.close();
	}
	@Test//测试额外SQL（使用内联映射让Mybatis自动发送额外的SQL）
	public void testGet() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
		Employee e = employeeMapper.get(1L);
		System.out.println(e);
		//System.out.println(e.getDept());
		session.close();
	}
	@Test//验证额外使用SQL造成的N+1问题
	public void testListAll1() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
		List<Employee> list = employeeMapper.anotherForListAll();
		for (Employee e : list) {
			System.out.println(e);
			System.out.println(e.getDept());
		}
		session.close();
	}
	@Test//使用多表查询来解决N+1问题
	public void testListAll2() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
		List<Employee> list = employeeMapper.listAll();
		for (Employee e : list) {
			System.out.println(e);
			System.out.println(e.getDept());
		}
		session.close();
	}
}
