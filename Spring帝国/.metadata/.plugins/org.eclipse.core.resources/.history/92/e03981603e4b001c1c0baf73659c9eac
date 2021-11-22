package com.yeqing.demo;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.yeqing.util.ClassUtil;

public class ClassScanUtilTest {

	@Test
	void test() throws Exception {
		String packageName = this.getClass().getPackageName();
		System.out.println(packageName);
		packageName = packageName.substring(0, packageName.lastIndexOf("."));
		System.out.println(packageName);
		Set<Class<?>> set = ClassUtil.getClassSet("com.yeqing");
		for (Class<?> clazz : set) {
			System.out.println(clazz.getName());
		}
		//File[] files = getClassFiles("/D:/eclipse-jee-2020-03-R-incubation-win32-x86_64/eclipse/workspace/JavaStudy/Spring%e5%b8%9d%e5%9b%bd/SpringMVC/MockSpringMVC/webapp/WEB-INF/classes/com/yeqing/hello");
	}
}
