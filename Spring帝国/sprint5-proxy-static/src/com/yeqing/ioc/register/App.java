package com.yeqing.ioc.register;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.yeqing.ioc.register.action.UserAction;
import com.yeqing.ioc.register.domain.User;

@SpringJUnitConfig
public class App {
	
	@Autowired
	private UserAction action;
	
	@Test
	void test() throws Exception {
		User u = new User();
		u.setUsername("张三");
		u.setAge(18);
		action.execute(u);
	}
}
