package com.yeqing._04_junit;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MyJUnit {
	public static <T> void myJunit(Class<T> classType) throws Exception {
		T obj = classType.newInstance();
		List<Method> beforeList = new ArrayList<>();
		List<Method> testList = new ArrayList<>();
		List<Method> afterList = new ArrayList<>();

		// 1、根据Class对象获得类的所有方法
		Method[] ms = classType.getMethods();

		// 2、迭代每一个方法，并判断出所有的被注释为：MyBefore、MyAfter、MyTest标签的方法，
		// 将它们分别放入beforeList、afterList、testList三个集合中
		for (Method method : ms) {
			if (method.isAnnotationPresent(MyBefore.class)) {
				beforeList.add(method);
			} else if (method.isAnnotationPresent(MyAfter.class)) {
				afterList.add(method);
			} else if (method.isAnnotationPresent(MyTest.class)) {
				testList.add(method);
			}
		}
		// 3、取出三个集合中的元素，利用反射机制按照“beforeList --> testList --> afterList”的顺序执行里面的每个方法
		for(Method tm : testList) {
			// 先执行所有被标注为MyBefore的方法
			for(Method bm : beforeList) {
				bm.invoke(obj);
			}
			// 再执行一个被标注为MyTest的方法
			tm.invoke(obj);
			// 然后执行所有被标注为MyAfter的方法
			for(Method am : afterList) {
				am.invoke(obj);
			}
		}
	}
}
