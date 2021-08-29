package com.yeqing._01_javabean;


import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.yeqing.smis.domain.Student;

// 因为JavaBean与Map有着很高的相似性（属性名对应key，属性值对应value），因此可以将它们进行互换操作
public class JavaBeanAndMap {
    /**
     * 将一个JavaBean对象转换成一个Map对象
     * @param javabean 一个JavaBean对象
     * @return  由该JavaBean对象转换的一个Map对象
     * @throws Exception 有异常就直接抛出
     */
	public static Map<String, Object> javabean2map(Object javabean) throws Exception {
		//创建一个Map对象
		Map<String, Object> map = new HashMap<String, Object>();
		//利用Javabean的内省机制获得它的BeanInfo对象
		BeanInfo beanInfo = Introspector.getBeanInfo(javabean.getClass(), Object.class);
		//通过该BeanInfo对象获得关于该JavaBean对象的所有属性的描述器
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		//遍历每个属性的描述器，获得属性名和属性值，并放入到Map对象中
		for (PropertyDescriptor pd : pds) {
			String name = pd.getName();
			//通过调用readableMethod方法获得属性值
			Object value = pd.getReadMethod().invoke(javabean);
			map.put(name, value);
		}
		return map;
	}
	
	/**
	 * 将一个Map对象转换为一个指定类型的JavaBean对象
	 * @param <T> 用一个泛型来代指要返回的JavaBean的类型
	 * @param map 需要被转换的map对象
	 * @param javabeanType 指定的JavaBean类型的Class对象
	 * @return 返回一个由map对象转换而来的，指定类型的JavaBean对象
	 * @throws Exception 有异常就直接抛出
	 */
	public static <T> T map2javabean(Map<String, Object> map, Class<T> javabeanType) throws Exception {
		//通过反射机制获得一个指定类型的JavaBean对象
		T obj = javabeanType.newInstance();
		//通过JavaBean的内省机制，获得BeanInfo对象
		BeanInfo beanInfo = Introspector.getBeanInfo(javabeanType, Object.class);
		//获得所有属性的描述器
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		//遍历每个属性的描述器，将map中key与属性名相同的元素的value，设置到JavaBean对象中
		for (PropertyDescriptor pd : pds) {
			//获取map中与属性名对应的属性值
			Object value = map.get(pd.getName());
			//通过调用writableMethod方法，将属性值设置到JavaBean对象中
			pd.getWriteMethod().invoke(obj, value);
		}
		return obj;
	}
	
	@Test
	public void testJavabean2map() throws Exception {
		Student s = new Student();
		s.setId(2L);
		s.setName("李四");
		s.setAge(19);
		System.out.println(s);
		System.out.println(javabean2map(s));
		
	}
	
	@Test
	public void testMap2javabean() throws Exception {
		Student s = new Student();
		s.setId(3L);
		s.setName("王五");
		s.setAge(18);
		Map<String, Object> map = javabean2map(s);
		System.out.println(map2javabean(map, Student.class));
	}
}
