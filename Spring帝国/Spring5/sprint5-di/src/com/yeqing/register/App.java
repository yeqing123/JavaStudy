package com.yeqing.register;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.yeqing.register.action.RegisterAction;
import com.yeqing.register.domain.User;

@SpringJUnitConfig
public class App {
    @Autowired
	private RegisterAction action;
    
    @Test
	void test() throws Exception {
    	User u = new User();
		u.setUsername("王五");
		u.setAge(20);
		action.excute(u);
	}
}
