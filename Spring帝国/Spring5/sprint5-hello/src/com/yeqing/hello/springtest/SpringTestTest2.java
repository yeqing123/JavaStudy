package com.yeqing.hello.springtest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yeqing.hello.HelloWorld;

//spring的第二种测试方式
@RunWith(SpringJUnit4ClassRunner.class)
//xml文件可以自定义
@ContextConfiguration("classpath:com/yeqing/hello/springtest/springTest.xml")
public class SpringTestTest2 {

	@Autowired
	private HelloWorld world;
	@Test
	public void test1() throws Exception {
		world.sayHello();
	}
}
