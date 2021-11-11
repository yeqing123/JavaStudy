package com.yeqing.createbean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.yeqing.createbean._01_constructor.Cat1;
import com.yeqing.createbean._02_static_factory.Cat2;
import com.yeqing.createbean._03_instance_factory.Cat3;
import com.yeqing.createbean._04_factory_bean.Cat4;

//测试四种Spring容器实例化Bean对象的方法
@SpringJUnitConfig
public class App {
	@Autowired
	private Cat1 c1;
	@Autowired
	private Cat2 c2;
	@Autowired
	private Cat3 c3;
	@Autowired
	private Cat4 c4;
	@Autowired
	private Cat4 c44;
	@Autowired
	private Cat4 c444;
	
	@Test//方法1：调动Bean的无参构造器，这是最普通最一般的方法
	void test1() throws Exception {
		System.out.println(c1);
	}
	@Test//方法2：使用静态工厂类，调用该工厂类中的静态方法
	void test2() throws Exception {
		System.out.println(c2);
	}
	@Test//方法3：使用实例工厂，调用工厂类对象中的方法来创建Bean对象
	void test3() throws Exception {
		System.out.println(c3);
	}
	@Test//方法4：使用实现了FactoryBean接口的工厂类，来实例化Bean对象（它是实例工厂的变种）
	void test4() throws Exception {
		System.out.println(c4);
	}
	
	@Test//使用方法4，如果多次实例化Bean对象，则是单例的。当然我们在覆写FactoryBean接口中的isSingleton方法来取消单例机制
	void test5() throws Exception {
		System.out.println(c4);
		System.out.println(c44);
		System.out.println(c444);
	}
}
