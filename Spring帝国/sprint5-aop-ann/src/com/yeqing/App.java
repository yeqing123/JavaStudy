package com.yeqing;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.yeqing.domain.Employee;
import com.yeqing.service.IEmployeeService;

@SpringJUnitConfig
public class App {
	
	@Autowired
	//如果真实对象没有接口，Spring会自动使用CGLIB的动态代理，否则默认使用JDK的动态代理
	private IEmployeeService service; 
	
	@Test
	void testSave() throws Exception {
		System.out.println(service.getClass());
		service.save(new Employee());
	}
	@Test
	void testUpdate() throws Exception {
		System.out.println(service.getClass());
		service.update(new Employee());
	}
}
