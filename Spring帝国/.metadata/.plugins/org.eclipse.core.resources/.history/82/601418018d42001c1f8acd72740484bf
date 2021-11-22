package com.yeqing.hello;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


public class HelloWorldTest {
	@Test//用传统方法测试HelloWord类
	void testOld() throws Exception {
		HelloWorld world = new HelloWorld();
		world.setUsername("yeqing");
		world.sayHello();
	}
	
	@Test//用Spring的控制反转，测试HelloWorld类
	void testIoC() throws Exception {
		HelloWorld world = null;
		//1.加载配置文件
		Resource resource = new ClassPathResource("applicationContext.xml");
		//2.使用加载的配置文件，创建Spring IoC容器
	    BeanFactory factory = new XmlBeanFactory(resource);
	    //3.通过spring IoC容器，得到名为“HelloWorld”的bean对象
	    world = (HelloWorld) factory.getBean("HelloWorld");
	    world.sayHello();
	}
	@Test//使用反射和内省机制模拟Spring
	void testMock() throws Exception {
		String className = "com.yeqing.hello.HelloWorld";
		//----------------------------------------------------------
		//通过反射机制获得Bean的无参构造器
	    Constructor<?> con = Class.forName(className).getConstructor();
	    //设置无参构造器的访问权限
	    con.setAccessible(true);
        //创建一个Bean对象
	    Object obj = con.newInstance();
	    //使用内省机制，获取对象的所有属性
		BeanInfo beanInfo = Introspector.getBeanInfo(Class.forName(className));
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor pd : pds) {
			String name = pd.getName();
			if("username".equals(name)) {
				pd.getWriteMethod().invoke(obj, "yeqing");
			} else if("age".equals(name)) {
				pd.getWriteMethod().invoke(obj, 19);
			}
		}
		//-----------------------------------------------------------
		HelloWorld world = (HelloWorld) obj;
		world.sayHello();
	}
}
