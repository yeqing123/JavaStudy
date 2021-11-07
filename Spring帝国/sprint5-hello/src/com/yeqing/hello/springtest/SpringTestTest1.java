package com.yeqing.hello.springtest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.yeqing.hello.HelloWorld;

@SpringJUnitConfig
public class SpringTestTest1 {
    @Autowired
	private HelloWorld world;
    
    @Test
	void test1() throws Exception {
		world.sayHello();
	}
}
