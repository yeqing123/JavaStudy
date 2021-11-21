package com.yeqing.scope;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
public class App {
	
	@Autowired
	private Dog d1;
	@Autowired
	private Dog d2;
	@Autowired
	private Dog d3;
	
	@Test//Spring容器默认是单例的，可以在XML文件中配置scope属性值为“prototype”设置为多例
	void test() throws Exception {
		System.out.println(d1);
		System.out.println(d2);
		System.out.println(d3);
	}
}
