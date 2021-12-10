package com.yeqing.ssm;


import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yeqing.ssm.domain.User;
import com.yeqing.ssm.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationConfig.xml")
public class AppTest {

	@Autowired
	private IUserService userService;
	
	@Test
	public void testListAll() throws Exception {
		userService.listAll().forEach(System.out::println);
	}
	@Test
	public void testTransaction() throws Exception {
		userService.save(new User(null, "bbb", 10, new Date(), ""));
	}
}
