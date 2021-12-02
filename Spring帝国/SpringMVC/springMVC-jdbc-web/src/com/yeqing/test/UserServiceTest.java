package com.yeqing.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.yeqing.mybatis.dao.IUserDAO;

@SpringJUnitConfig(locations = "classpath:applicationConfig.xml")
public class UserServiceTest {
    
	@Autowired
	private IUserDAO dao;
	
	@Test
	void testCheckLogin() throws Exception {
		System.out.println(dao.checkLogin("叶青", "1111"));
	}
}
