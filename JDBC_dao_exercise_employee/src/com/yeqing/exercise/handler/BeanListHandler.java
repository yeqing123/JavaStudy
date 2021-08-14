package com.yeqing.exercise.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yeqing.exercise.ann.Column;

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
					Field field = classType.getDeclaredField(pd.getName());
					Column ann = field.getAnnotation(Column.class);
					String columnName = pd.getName();
					if(ann != null) {  // 如果对象的属性名与列名不一致，则使用字段上的@Column注释标注对应的列名
						columnName = ann.value();
					}
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
