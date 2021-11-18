package com.yeqing;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.yeqing.service.IAccountService;

@SpringJUnitConfig
public class App {
	
	@Autowired
	private IAccountService accountService;
	
	@Test
	void test() throws Exception {
		accountService.transfer(10086L, 10010L, 1000);
	}
}
