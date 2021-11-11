package com.yeqing.hello.springtest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.yeqing.hello.HelloWorld;

//spring的第一中测试方式，使用该种方式时XML配置文件必须是Xxx-context.xml的命名形式
@SpringJUnitConfig
public class SpringTestTest1 {
    @Autowired
	private HelloWorld world;
    
    @Test
	void test1() throws Exception {
		world.sayHello();
	}
}
