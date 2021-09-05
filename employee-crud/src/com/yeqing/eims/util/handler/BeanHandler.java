package com.yeqing.eims.util.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.ResultSet;

import com.yeqing.eims.util.ann.ColumnName;


//处理查询指定信息后返回的结果集
public class BeanHandler<T> implements IResultSetHandler<T> {
    private Class<T> javabeanType;
    public BeanHandler(Class<T> javabeanType) {
    	this.javabeanType = javabeanType;
    }
	public T handler(ResultSet rs) {
        try {
        	if(rs.next()) { 
        		//利用反射机制，实例化一个对象
        		T obj = javabeanType.newInstance();
        		//利用内省机制获取所有的属性描述符
        		BeanInfo beanInfo = Introspector.getBeanInfo(javabeanType, Object.class);
        		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        		//遍历每一个属性描述符，并获取与属性名对应的类名，如果有Column注解就用注解中标识的列名，如果没有则默认属性名作为类名
        		for (PropertyDescriptor pd : pds) {
        			String name = pd.getName();   
					Field f = javabeanType.getDeclaredField(name);
					ColumnName ann = f.getAnnotation(ColumnName.class);
					if(ann != null) {
						name = ann.value();
					}
					//从结果集中获取属性值，并调用Setter方法设置到对象中
					pd.getWriteMethod().invoke(obj, rs.getObject(name));
				}
        		return obj;
        	}
        }catch(Exception e) {
        	e.printStackTrace();
        }
		return null;
	}

}
