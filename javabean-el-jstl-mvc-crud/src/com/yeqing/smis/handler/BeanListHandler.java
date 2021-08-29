package com.yeqing.smis.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


// 处理包含多行信息的结果集通用处理器
public class BeanListHandler<T> implements IResultSetHandler<List<T>> {

    private Class<T> classType = null;
    
	public BeanListHandler(Class<T> classType) {
    	this.classType = classType;
    }
	
	@Override
	public List<T> handler(ResultSet rs) {
		List<T> list = new ArrayList<>();
		try {
			BeanInfo info = Introspector.getBeanInfo(classType, Object.class);
			PropertyDescriptor[] pds = info.getPropertyDescriptors();
			while(rs.next()) {
				T obj = classType.newInstance();  // 实例化对象
				// 利用内省机制，设置对象属性
				for (PropertyDescriptor pd : pds) {
					String columnName = pd.getName();
					pd.getWriteMethod().invoke(obj, rs.getObject(columnName)); // 从结果集获取信息，并写入到对象中
				}
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
