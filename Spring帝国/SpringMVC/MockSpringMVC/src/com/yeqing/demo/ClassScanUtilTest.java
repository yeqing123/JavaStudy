package com.yeqing.demo;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.yeqing.mockmvc.annotation.Controller;
import com.yeqing.util.ClassUtil;

public class ClassScanUtilTest {

	@Test
	void test() throws Exception {
		String packageName = this.getClass().getPackage().getName();
		System.out.println(packageName);
		packageName = packageName.substring(0, packageName.lastIndexOf("."));
		System.out.println(packageName);
		List<Class<?>> list = ClassUtil.getClassListByAnnotation("com.yeqing", Controller.class);
		for (Class<?> clazz : list) {
			System.out.println(clazz.getName());
		}
		//File[] files = getClassFiles("/D:/eclipse-jee-2020-03-R-incubation-win32-x86_64/eclipse/workspace/JavaStudy/Spring%e5%b8%9d%e5%9b%bd/SpringMVC/MockSpringMVC/webapp/WEB-INF/classes/com/yeqing/hello");
	}
}
