package com.yeqing.sims.util.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;

import com.yeqing.sims.ann.Column;


/**
 *  处理包含了一条指定信息的结果集
 * @author yeqin
 *
 * @param <T> 指定的对象类型
 */
public class BeanResultSetHandler<T> implements IResultSetHandler<T> {
	private Class<T> javabeanType = null;
	public BeanResultSetHandler(Class<T> javabeanType) {
		this.javabeanType = javabeanType;
	}
	public T handler(ResultSet rs) {
		try {
			T obj = javabeanType.newInstance(); // 利用反射机制，实例化一个JavaBean对象
			// 需要使用JavaBean的内省机制来设置对象信息
			BeanInfo beanInfo = Introspector.getBeanInfo(javabeanType, Object.class);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			if(rs.next()) {
				for (PropertyDescriptor pd : pds) {
					String name = pd.getName();  // 获取属性名
					Column ann = javabeanType.getDeclaredField(name).getAnnotation(Column.class);  //获取Column注解
					if(ann != null) { //如果有该注解，说明属性名与结果集中的列名不一致
						name = ann.value();
					}
					pd.getWriteMethod().invoke(obj, rs.getObject(name)); //通过列名从结果集中获取属性值，设置到对象中
				}
				return obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
