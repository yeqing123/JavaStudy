package com.yeqing.ioc.lifecycle;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
public class App {
	
	@Autowired
	private MyDataSource myDataSource;
	
	@Test
	void test() throws Exception {
		myDataSource.doWork();
	}
}
