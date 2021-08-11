package com.yeqing.jdbc.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;

public class BeanHandler<T> implements IResultSetHandler<T> {
	private Class<T> classType = null;

	public BeanHandler(Class<T> classType) {
		    this.classType = classType;
		}

	public T handler(ResultSet rs) {
		// 1,将结果集中的处理标记下移一行
		try {
			if (rs.next()) {
				// 2,创建要处理的每行数据的（domain）类型实例
				T obj = classType.newInstance();
				// 利用内省机制完成3,4两步
				BeanInfo info = Introspector.getBeanInfo(classType, Object.class);
				PropertyDescriptor[] pds = info.getPropertyDescriptors();
				for (PropertyDescriptor pd : pds) {
					String columnName = pd.getName();
					// 3，从结果集中取出唯一的一行数据，封装成一个对象
					Object val = rs.getObject(columnName);
					// 4，将取出的数据设置到domain对象中
					pd.getWriteMethod().invoke(obj, val);
				}
				// 5,因为只有一行数据，所以直接返回对象
				return obj;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
