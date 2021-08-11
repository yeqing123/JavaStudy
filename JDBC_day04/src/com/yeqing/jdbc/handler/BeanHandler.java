package com.yeqing.jdbc.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.sql.ResultSet;

import com.alibaba.druid.stat.TableStat.Column;

public class BeanHandler<T> implements IResultSetHandler<T> {
    private Class<T> classType = null; 
	public BeanHandler(Class<T> classType) {
	    this.classType = classType;
	}

	public T handler(ResultSet rs) {
		try {
		//1,因为结果集中只有一行数据，因此只需将处理标记下移一次
			if(rs.next()) {
				//2,创建要处理的每行数据的（domain）类型实例
			    T obj = classType.newInstance();
			    //利用内省机制完成3,4两步
			    BeanInfo info = Introspector.getBeanInfo(classType, Object.class);
			    PropertyDescriptor[] pds = info.getPropertyDescriptors();
			    //3，从结果集中取出唯一的一行数据
			    for (PropertyDescriptor pd : pds) {
			    	Annotation[] anns = pd.getClass().getAnnotation(Column.class);
			    	for (int i = 0; i < pds.length; i++) {
						System.out.println(anns[i]);
					}
			    	//String columnName = 
					//String propertyName = pd.getName();  //将属性名作为列名
					
				//	Object val = rs.getObject(columnName);  // 获取列名对应的值
					//4，将取出的数据设置到domain对象中
				//	pd.getWriteMethod().invoke(obj, val);
				}
				//5，直接返回结果
			    return obj;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
