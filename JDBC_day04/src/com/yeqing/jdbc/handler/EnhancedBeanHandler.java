package com.yeqing.jdbc.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.ResultSet;

import com.yeqing.jdbc.ann.Column;

// 这是一款升级版的“结果集处理器”，它可以利用JavaBean中字段上的注释，自动处理字段名与表的列名不一致造成的问题。
public class EnhancedBeanHandler<T> implements IResultSetHandler<T> {
    private Class<T> classType = null; 
	public EnhancedBeanHandler(Class<T> classType) {
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
			    for (int i = 0; i < pds.length; i++) {
			    	String columnName = null;
			    	Field field = classType.getDeclaredField(pds[i].getName());  //获取与属性名对应的字段对象 
					Column ann = field.getAnnotation(Column.class); // 获取字段上的Column注释
					if(ann != null) {  
                        columnName = ann.value(); // 如果该字段上标记了Column注释，则用该注释的value值作为列名
					} else {
					    columnName = pds[i].getName();  // 否则，用属性名作为列名
					}
					Object val = rs.getObject(columnName);  // 获取列名对应的值
					//4，将取出的数据设置到domain对象中
					pds[i].getWriteMethod().invoke(obj, val);
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
