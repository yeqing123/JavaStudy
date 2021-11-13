package com.yeqing.di;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
public class App {
	
	@Autowired
	private Person person;
	
	@Test
	void test() throws Exception {
		System.out.println(person);
	}
}
