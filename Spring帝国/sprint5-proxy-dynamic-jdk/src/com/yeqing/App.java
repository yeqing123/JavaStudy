package com.yeqing;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.yeqing.domain.Employee;
import com.yeqing.service.IEmployeeService;
import com.yeqing.tx.TransactionAdvice;

@SpringJUnitConfig
public class App {
	
	@Autowired
	private TransactionAdvice advice;
	
	@Test
	void testSave() throws Exception {
		IEmployeeService proxy = advice.getProxyObject();
		proxy.save(new Employee());
	}
	@Test
	void testUpdate() throws Exception {
		IEmployeeService proxy = advice.getProxyObject();
		proxy.update(new Employee());
	}
}
