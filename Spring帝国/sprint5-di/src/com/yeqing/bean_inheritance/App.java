package com.yeqing.bean_inheritance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


//需求：通过构造器来注入属性
@SpringJUnitConfig
public class App {
	@Autowired
	private TagBean1 bean1;
	@Autowired
	private TagBean2 bean2;

	@Test
	void test() throws Exception {
		System.out.println(bean1);
		System.out.println(bean2);
	}
}
