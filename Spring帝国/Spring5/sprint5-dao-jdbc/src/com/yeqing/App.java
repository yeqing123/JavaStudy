package com.yeqing;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.yeqing.dao.IEmployeeDAO;
import com.yeqing.domain.Employee;

@SpringJUnitConfig
public class App {
	
	@Autowired
	private IEmployeeDAO dao;
	
	@Test
	void testSave() throws Exception {
		Employee e = new Employee();
		e.setName("乔峰");
		e.setAge(30);
		dao.save(e);
	}
	@Test
	void testUpdate() throws Exception {
		Employee e = new Employee();
		e.setName("西门吹雪");
		e.setAge(25);
		e.setId(6L);
		dao.update(e);
	}
	@Test
	void testDelete() throws Exception {
		dao.delete(3L);
	}
	@Test
	void testGet() throws Exception {
		Employee e = dao.get(3L);
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
