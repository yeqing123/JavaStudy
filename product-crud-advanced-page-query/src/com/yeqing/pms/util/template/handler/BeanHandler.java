package com.yeqing.pms.util.template.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;

import com.yeqing.pms.util.ann.Column;



public class BeanHandler<T> implements IResultSetHandler<T> {
    private Class<T> classType;
	public BeanHandler(Class<T> classType) {
    	this.classType = classType;
    }
	public T handler(ResultSet rs) {
		try {
			if (rs.next()) {
				T obj = classType.newInstance();
				//使用JavaBean的内省机制（记住它的核心类：Introspector），设置对象属性
			    BeanInfo beanInfo = Introspector.getBeanInfo(classType, Object.class);
			    PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors(); //获取所有的属性描述符
			    //遍历每个描述符，将从结果集中获取的数据设置到对象中
			    for (PropertyDescriptor pd : pds) {
					String name = pd.getName(); //获取属性名
					Column ann = classType.getDeclaredField(name).getAnnotation(Column.class);
					if(ann != null) {  //如果专门为该属性设置的Column注解，就使用注解指定的列名
						name = ann.value();
					}
					//从结果集中获取数据，并设置到对象中
					pd.getWriteMethod().invoke(obj, rs.getObject(name));
				}
			    return obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
