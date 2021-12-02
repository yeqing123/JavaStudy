package com.yeqing.test;


import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.yeqing.domain.Employee;
import com.yeqing.service.impl.EmployeeServiceImpl;

@SpringJUnitConfig(locations = "classpath:applicationConfig.xml")
public class EmployeeServiceTest {
    @Autowired
	private EmployeeServiceImpl service;
    
	@Test
	void testSave() throws Exception {
		Employee e = new Employee();
		e.setUsername("张三");
		e.setAge(19);
		e.setHiredate(new Date());
		service.save(e);
	}
	@Test
	void testDelete() throws Exception {
		service.delete(6L);
	}
	@Test
	void testUpdate() throws Exception {
		Employee e = service.get(4L);
		e.setUsername("张三丰");
		e.setAge(100);
		e.setHiredate(new Date());
		service.update(e);
	}
	@Test
	void testGet() throws Exception {
		Employee e = service.get(1L);
		System.out.println(e);
	}
	@Test
	void testListAll() throws Exception {
		List<Employee> list = service.listAll();
		for (Employee e : list) {
			System.out.println(e);
		}
	}
}
