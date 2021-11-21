package com.yeqing.container;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

//比较BeanFactory接口和ApplicationContext接口，对于IoC容器中对象的加载方式和使用方法
public class App {

	@Test//BeanFactory接口使用的延迟初始化，即只有当使用bean时才实例化
	void testBeanFactory() throws Exception {
		Resource resource = new ClassPathResource("com/yeqing/container/Container-context.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		System.out.println("----Before created bean---");
		Person person = factory.getBean("Person", Person.class);
		System.out.println("---After created bean---");
		System.out.println(person);
	}
	
	/*
	 * 相比BeanFactory接口ApplicationContext接口，在启动容器的时候就将全部的Bean对象实例化，这种方法用的最多，
	 * 因为一旦服务器启动后容器中就已经创建好了所有的对象，可以提高服务器的响应速度
	 */
	@Test
	void testApplicationContext() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("com/yeqing/container/Container-context.xml");
		System.out.println("----Before created bean---");
		Person person = ctx.getBean("Person", Person.class);
		System.out.println("---After created bean---");
		System.out.println(person);
	}
}
