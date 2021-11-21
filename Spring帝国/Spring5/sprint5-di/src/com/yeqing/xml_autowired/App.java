package com.yeqing.xml_autowired;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

//需求：通过XML的自动装配机制（主要是配置XML文件中的autowired元素值，不推荐）
@SpringJUnitConfig
public class App {
	@Autowired
	private Person person;
	
	@Test
	void test() throws Exception {
		System.out.println(person);
	}
}
