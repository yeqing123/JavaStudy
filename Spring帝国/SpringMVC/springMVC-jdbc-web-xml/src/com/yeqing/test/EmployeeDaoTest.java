package com.yeqing.test;


import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.yeqing.dao.IEmployeeDAO;
import com.yeqing.dao.impl.EmployeeDAOImpl;
import com.yeqing.domain.Employee;

public class EmployeeDaoTest {

	private IEmployeeDAO dao = new EmployeeDAOImpl();
	@Test
	void testSave() throws Exception {
		Employee e = new Employee();
		e.setUsername("张三");
		e.setAge(19);
		e.setHiredate(new Date());
		dao.save(e);
	}
	@Test
	void testDelete() throws Exception {
		dao.delete(6L);
	}
	@Test
	void testUpdate() throws Exception {
		Employee e = dao.get(4L);
		e.setUsername("张三丰");
		e.setAge(100);
		e.setHiredate(new Date());
		dao.update(e);
	}
	@Test
	void testGet() throws Exception {
		Employee e = dao.get(1L);
		System.out.println(e);
	}
	@Test
	void testListAll() throws Exception {
		List<Employee> list = dao.listAll();
		for (Employee e : list) {
			System.out.println(e);
		}
	}
}
