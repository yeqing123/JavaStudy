package com.yeqing.ssm;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yeqing.ssm.domain.User;
import com.yeqing.ssm.mapper.UserMapper;
import com.yeqing.ssm.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationConfig.xml")
public class AppTest {
	
	@Autowired
	private UserMapper mapper;
	@Autowired
	private IUserService service;
	
	@Test
	public void test1() throws Exception {
		List<User> list = mapper.selectAll();
		for (User user : list) {
			System.out.println(user);
		}
	}
	@Test  //测试事务增强是否配置成功
	public void test2() throws Exception {
		User u = new User();
		u.setName("AAA");
		u.setAge(20);
		service.save(u);
	}
}
