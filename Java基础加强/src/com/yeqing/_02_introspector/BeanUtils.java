package com.yeqing._02_introspector;

import java.beans.*;
import java.util.HashMap;
import java.util.Map;

import com.yeqing._01_javabean.Person;

public class BeanUtils {

	public static void main(String[] args) throws Exception {
        Person p = new Person();
        p.setId(123L);
        p.setName("张三");
        p.setAge(18);
        
        Map<String, Object> map = bean2map(p);
        System.out.println(map);
        
        Person p2 = map2bean(map, Person.class);
        System.out.println(p2);
	}
	
	// 将一个JavaBean转换成Map集合
    public static Map<String, Object> bean2map(Object bean) throws Exception {
    	Map<String, Object> map = new HashMap<>();
    	// 通过内省机制获得JavaBean对象中包含的属性、方法等
    	BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
    	// 获取bean的所有属性的描述
    	PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
    	
    	for (PropertyDescriptor pd : pds) {
			String name = pd.getName();
			Object value = pd.getReadMethod().invoke(bean);
			map.put(name, value);
		}
    	return map;
    }
    
    // 将Map集合转换成JavaBean
    public static <T> T map2bean(Map<String, Object> map, Class beanType) throws Exception {
    	Object obj = beanType.newInstance();
    	// 通过内省机制获得JavaBean对象中包含的属性、方法等
    	BeanInfo beanInfo = Introspector.getBeanInfo(beanType, Object.class);
    	// 获取bean的所有属性的描述
    	PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
    	for (PropertyDescriptor pd : pds) {
    		// 获取map中与key对象的值
			Object value = map.get(pd.getName());
			// 利用反射机制，调用参数obj中的setter方法，设置属性值为value
			pd.getWriteMethod().invoke(obj, value);
		}
    	return (T)obj;
    }
}
