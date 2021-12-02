package com.yeqing.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.yeqing.service.IUserService;

@SpringJUnitConfig(locations = "classpath:applicationConfig.xml")
public class UserServiceTest {
    
	@Autowired
	private IUserService service;
	
	@Test
	void testCheckLogin() throws Exception {
		System.out.println(service.checkLogin("刘莎", "2222"));
	}
}
