package com.yeqing.di_setter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

//需求：通过属性（setter）注入
@SpringJUnitConfig
public class App {
	@Autowired
	private Employee1 employee1;
	@Autowired
	private Employee2 employee2;
//	@Autowired
	private Cat cat;
	@Autowired
	private Employee3 employee3;
	
	@Test
	void test() throws Exception {
		System.out.println(employee1);
		System.out.println(employee2);
		System.out.println(cat);
		System.out.println(employee3);
	}
}
