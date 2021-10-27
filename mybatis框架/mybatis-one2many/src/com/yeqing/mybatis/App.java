package com.yeqing.mybatis;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yeqing.mybatis.domain.Department;
import com.yeqing.mybatis.domain.Employee;
import com.yeqing.mybatis.mapper.DepartmentMapper;
import com.yeqing.mybatis.mapper.EmployeeMapper;
import com.yeqing.mybatis.util.MybatisUtil;

public class App {
	@Test//先来测试添加新部门和新员工，并设置它们的“一对多”关系
	public void testSave() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
		DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
		//必须先创建并保存部门，而后在保存员工，因为员工对象中需要设置部门的主键值
		Department d = new Department();
		d.setName("开发部");
		departmentMapper.save(d);
		//创建该部门所属员工
		Employee e1 = new Employee();
		Employee e2 = new Employee();
		e1.setName("张三");
		e1.setDeptId(d.getId());
		e2.setName("李四");
		e2.setDeptId(d.getId());
		employeeMapper.save(e1);
		employeeMapper.save(e2);
		session.commit();
		session.close();
	}
	@Test//使用额外的SQL，查询指定部门的信息（练习开启延迟加载功能）
	public void testGet1() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
		Department d = departmentMapper.get4another(10L);
		System.out.println(d);
		//如果在配置文件中开启了延迟加载功能，如果使用部门的员工集合就不会进行额外的SQL查询
		//System.out.println(d.getEmps());
		session.close();
	}
	@Test//使用多表查询（内联查询），查询指定部门信息
	public void testGet2() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
		Department d = departmentMapper.get4join(10L);
		System.out.println(d);
		System.out.println(d.getEmps());
		session.close();
	}
}
