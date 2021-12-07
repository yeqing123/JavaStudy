package com.yeqing.test;


import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.yeqing.domain.Employee;
import com.yeqing.service.IEmployeeService;

@SpringJUnitConfig(classes = {com.yeqing.MyWebInitializer.class, 
		com.yeqing.RootConfig.class, com.yeqing.WebConfig.class},
		inheritLocations = true)
public class EmployeeServiceTest {
    @Autowired
	private IEmployeeService service;
    
	@Test
	void testSave() throws Exception {
		Employee e = new Employee();
		e.setUsername("李四");
		e.setAge(30);
		e.setHiredate(new Date());
		service.save(e);
	}
	@Test
	void testDelete() throws Exception {
		service.delete(10L);
	}
	@Test
	void testUpdate() throws Exception {
		Employee e = service.get(10L);
		e.setUsername("BBB");
		e.setAge(1);
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
