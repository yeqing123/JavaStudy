package com.yeqing.ssm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yeqing.ssm.domain.User;
import com.yeqing.ssm.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationConfig.xml")
public class AppTest {
	
	@Autowired
	private IUserService service;
	
	@Test
	public void test1() throws Exception {
		service.listAll().forEach(System.out::println);
	}

	@Test  //测试事务增强是否配置成功
	public void test2() throws Exception {
		User u = new User();
		u.setName("Lucy");
		u.setAge(20);
		service.save(u);
	}
}
