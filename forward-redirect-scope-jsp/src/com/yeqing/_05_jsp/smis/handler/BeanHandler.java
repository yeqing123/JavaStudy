package com.yeqing._05_jsp.smis.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;




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
					String columnName = pd.getName();
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
