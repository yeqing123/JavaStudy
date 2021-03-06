package com.yeqing;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.yeqing.domain.Employee;
import com.yeqing.interceptor.LogAdvice;
import com.yeqing.service.EmployeeService;

@SpringJUnitConfig
public class App {
	
	@Autowired
	private LogAdvice advice;
	
	@Test
	void testSave() throws Exception {
		EmployeeService proxy = advice.getProxyObject();
		System.out.println(proxy.getClass());
		proxy.save(new Employee());
	}
	@Test
	void testUpdate() throws Exception {
		EmployeeService proxy = advice.getProxyObject();
		proxy.update(new Employee());
	}
}
