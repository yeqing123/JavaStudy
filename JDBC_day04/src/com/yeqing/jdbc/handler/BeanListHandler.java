package com.yeqing.jdbc.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BeanListHandler<T> implements IResultSetHandler<List<T>> {
	private Class<T> classType = null;

	public BeanListHandler(Class<T> classType) {
		    this.classType = classType;
		}

	public List<T> handler(ResultSet rs) {
		List<T> list = new ArrayList<>();
		// 1,将结果集中的处理标记下移一行，如果已经是最后一行了返回list
		try {
			while (rs.next()) {
				// 2,创建要处理的每行数据的（domain）类型实例
				T obj = classType.newInstance();
				// 利用内省机制完成3,4两步
				BeanInfo info = Introspector.getBeanInfo(classType, Object.class);
				PropertyDescriptor[] pds = info.getPropertyDescriptors();
				for (PropertyDescriptor pd : pds) {
					String columnName = pd.getName();
					// 3，从结果集中的一行中取出数据
					Object val = rs.getObject(columnName);
					// 4，将取出的数据设置到domain对象中
					pd.getWriteMethod().invoke(obj, val);
				}
				// 5,将该对象加入到list集合中
				list.add(obj);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
