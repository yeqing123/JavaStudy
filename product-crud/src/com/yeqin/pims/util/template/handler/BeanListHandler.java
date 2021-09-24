package com.yeqin.pims.util.template.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yeqin.pims.ann.ColumnName;


//结果姐处理器，返回包含了封装了多条信息的对象的List集合
public class BeanListHandler<T> implements IResultSetHandler<List<T>> {
	
	private Class<T> beanClass;  //封装信息的对象的类型
    public BeanListHandler(Class<T> beanClass) {
    	this.beanClass = beanClass;
    }
    
	public List<T> handler(ResultSet rs) {
		List<T> list = new ArrayList<>();
        try {
        	//获取javaBean对象的所有属性的描述符
        	BeanInfo beanInfo = Introspector.getBeanInfo(beanClass, Object.class);//记住内省机制的核心类：Introspecator
        	PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        	//从结果集中逐行取出数据，并将每行数据封装到对象中，然后放入List集合中
        	while(rs.next()) {
        		//创建一个封装对象
        	    T obj = beanClass.newInstance();
        		//遍历每个属性描述符，获取与之对应的列名
        	    for(PropertyDescriptor pd : pds) {
        	    	String name = pd.getName();
        	    	ColumnName ann = beanClass.getDeclaredField(name).getAnnotation(ColumnName.class);
        	    	if(ann != null) {
        	    		name = ann.value();
        	    	}
        	    	//根据列名从结果集中获取该列信息，并设置到对象中
        	    	pd.getWriteMethod().invoke(obj, rs.getObject(name));
        	    }
        	    //将对象放入到List集合中
        	    list.add(obj);
        	}
        }catch(Exception e) {
        	e.printStackTrace();
        }
		return list;  //返回List集合
	}

}
