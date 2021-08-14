package com.yeqing.exercise.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.ResultSet;

import com.yeqing.exercise.ann.Column;

// 处理只包含一行信息的结果集通用处理器
public class BeanHandler<T> implements IResultSetHandler<T> {
	
    private Class<T> classType = null;
    
	public BeanHandler(Class<T> classType) {
    	this.classType = classType;
    }
	
	@Override
	public T handler(ResultSet rs) {
		try {
			if(rs.next()) {
				T obj = classType.newInstance();  // 实例化对象
				// 利用内省机制，设置对象属性
				BeanInfo info = Introspector.getBeanInfo(classType, Object.class);
				PropertyDescriptor[] pds = info.getPropertyDescriptors();
				for (PropertyDescriptor pd : pds) {
					Field field = classType.getDeclaredField(pd.getName());  // 获取与属性名对应的字段对象
					Column ann = field.getAnnotation(Column.class);
					String columnName = pd.getName();
					if(ann != null) {  // 如果某一字段上加了@Column注释，则使用注释中的列名，否则默认以该字段名为列名
						columnName = ann.value();
					}
					pd.getWriteMethod().invoke(obj, rs.getObject(columnName)); // 从结果集获取信息，并写入到对象中
				}
				return obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
