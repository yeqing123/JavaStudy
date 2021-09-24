package com.yeqin.pims.util.template.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;

import com.yeqin.pims.ann.ColumnName;

//结果集处理器，返回一个封装了一条信息的对象
public class BeanHandler<T> implements IResultSetHandler<T> {
    private Class<T> beanClass;  //封装信息的对象的类型
    public BeanHandler(Class<T> beanClass) {
    	this.beanClass = beanClass;
    }
	public T handler(ResultSet rs) {
		try {
			if(rs.next()) {
				//创建JavaBean类型实例
				T obj = beanClass.newInstance();
				//获取类型的所有属性描述符
				BeanInfo beanInfo = Introspector.getBeanInfo(beanClass, Object.class);
				PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
				//遍历每一个属性描述符，从结果集中去数据，封装到对象中
				for (PropertyDescriptor pd : pds) {
					String name = pd.getName(); //获取属性名
					ColumnName ann = beanClass.getDeclaredField(name).getAnnotation(ColumnName.class); //获取该属性上的ColumnName注解
					if(ann != null) { //如果ColumnName存在，则以注解给出的名称作为列名
						name = ann.value();
					}
					pd.getWriteMethod().invoke(obj, rs.getObject(name));  //从结果集中获取数据，设置到对象中
				}
				return obj;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
