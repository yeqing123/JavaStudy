package com.yeqing.eims.util.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yeqing.eims.util.ann.ColumnName;


//处理结果集，返回一个包含所有信息对象的List集合
public class BeanListHandler<T> implements IResultSetHandler<List<T>> {
    private Class<T> javabeanType;
    public BeanListHandler(Class<T> javabeanType) {
    	this.javabeanType = javabeanType;
    }
	public List<T> handler(ResultSet rs) {
		List<T> list = new ArrayList<>();
		try {
			//利用内省机制，获取javabean的所有属性描述符
			BeanInfo beanInfo = Introspector.getBeanInfo(javabeanType, Object.class);
    		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
    		//循环遍历结果集中每行数据，每获取一行数据就封装成一个对象，放入到List集合中
    		while(rs.next()) {
    			T obj = javabeanType.newInstance();
				//遍历所有的属性描述符，并从结果集获取数据，设置到对象中
	    		for (PropertyDescriptor pd : pds) {
					String name = pd.getName();
					ColumnName ann = javabeanType.getDeclaredField(name).getAnnotation(ColumnName.class);
					if(ann != null) { //如果有ColumnName，则使用注解指定的列名
						name = ann.value();
					}
					pd.getWriteMethod().invoke(obj, rs.getObject(name)); //从结果获取数据，设置到对象中
				}
	    		list.add(obj); //放入List集合中
    		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
