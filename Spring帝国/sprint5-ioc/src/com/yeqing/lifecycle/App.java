package com.yeqing.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
//Spring可以帮我们自动调用初始化方法和销毁对象前的扫尾操作，我们只需要调用工作方法即可
public class App {
	
	@Autowired
    MyDataSource dataSource;
	
	@Test
	void testIoC1() throws Exception {
		dataSource.dowork();
	}
	
	@Test
	void testIoC2() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("com/yeqing/lifecycle/App-context.xml");
		MyDataSource ds = ctx.getBean("ds", MyDataSource.class);
		ds.dowork();
	}
	@Test
	void test3() throws Exception {
		Resource resource = new ClassPathResource("com/yeqing/lifecycle/App-context.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		MyDataSource ds = factory.getBean("ds", MyDataSource.class);
		ds.dowork();
		
	}
}
