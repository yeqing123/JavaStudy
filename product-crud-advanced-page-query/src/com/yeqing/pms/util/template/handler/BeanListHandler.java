package com.yeqing.pms.util.template.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yeqing.pms.util.ann.Column;



public class BeanListHandler<T> implements IResultSetHandler<List<T>> {
    private Class<T> classType;
    
	public BeanListHandler(Class<T> classType) {
		this.classType = classType;
	}

	public List<T> handler(ResultSet rs) {
		List<T> list = new ArrayList<>();
		try {
			while(rs.next()) {
				//1：利用内省机制，获取javaBean对象的所有属性描述符
				BeanInfo beanInfo = Introspector.getBeanInfo(classType, Object.class);
				PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
				//2:遍历每个描述符，并从结果集中获取每列数据，并封装到javabean对象中
				T obj = classType.newInstance();  //实例化一个javabean对象
				for (PropertyDescriptor pd : pds) {
					String name = pd.getName();
					Column ann = classType.getDeclaredField(name).getAnnotation(Column.class);
					if(ann != null) {
						name = ann.value();
					}
					//从结果集中获取数据，并设置到对象属性中
					pd.getWriteMethod().invoke(obj, rs.getObject(name));
				}
				//3:将每个javabean对象放入List集合中
				list.add(obj);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
