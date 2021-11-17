package com.yeqing;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.yeqing.domain.Employee;
import com.yeqing.service.IEmployeeService;

@SpringJUnitConfig
public class App {
	
	@Autowired
	private IEmployeeService service;
	
	@Test
	void testSave() throws Exception {
		System.out.println(service.getClass());
		service.save(new Employee());
	}
	@Test
	void testUpdate() throws Exception {
		service.update(new Employee());
	}
}
