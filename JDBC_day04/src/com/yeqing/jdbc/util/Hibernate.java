package com.yeqing.jdbc.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.yeqing.jdbc.ann.Column;
import com.yeqing.jdbc.ann.Id;
import com.yeqing.jdbc.ann.Table;
import com.yeqing.jdbc.handler.EnhancedBeanHandler;
import com.yeqing.jdbc.handler.EnhancedBeanListHandler;

// 模拟Hibernate工具
public class Hibernate {
	
	// 获取表名（如果使用了@Table注释，就使用注释中的表名），否则默认表名为对象名称
	private static String getTableName(Class<?> classType) {
		String tableName = classType.getSimpleName();
		Table ann = classType.getAnnotation(Table.class);
		if (ann != null) {
			tableName = ann.value();
		}
		return tableName;
	}
	
	public static void save(Object obj) {
		try {
			// 获取表名
			String tableName = getTableName(obj.getClass());
			// 使用内省机制获取所有的属性名和属性值
			BeanInfo info = Introspector.getBeanInfo(obj.getClass(), Object.class);
			PropertyDescriptor[] pds = info.getPropertyDescriptors();
			StringBuilder propertyName = new StringBuilder(); // 用于拼接属性名称
			StringBuilder mark = new StringBuilder(); // 用于拼接问号
			List<Object> list = new ArrayList<>(); // 用于存放属性值
			for (PropertyDescriptor pd : pds) {
				Field field = obj.getClass().getDeclaredField(pd.getName()); // 获取属性名对应的Field对象
				Id id = field.getAnnotation(Id.class); // 获取Id注释，表示主键列
				if (id == null) { // 主键是自动生成的，所以不需要手动设置
					Column column = field.getAnnotation(Column.class); // 判断该字段上是否贴了Column注释，如果贴了就用注释中的列名，否则用属性名作为列名
					if (column != null) {
						propertyName.append(column.value()).append(",");
					} else {
						propertyName.append(pd.getName()).append(",");
					}
					list.add(pd.getReadMethod().invoke(obj));
					mark.append("?,");
				}
			}
			propertyName.deleteCharAt(propertyName.length() - 1); // 去掉最后一个逗号
			mark.deleteCharAt(mark.length() - 1); // 去掉最后一个逗号

			// 拼接SQL
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO " + tableName + " (");
			sql.append(propertyName);
			sql.append(") VALUES (");
			sql.append(mark);
			sql.append(")");
			// 使用模板工具执行SQL
			JdbcTemplate.update(sql.toString(), list.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void delete(Long id, Class<?> classType) {
		// 获取表名
		String tableName = getTableName(classType);
		// 根据Id注释获取主键的列名
		String pkName = "id";
		Field[] fs = classType.getDeclaredFields();
		for (Field field : fs) {
			Id annId = field.getAnnotation(Id.class);
			if (annId != null) {
				pkName = field.getName();
			}
		}
		// 拼接SQL
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM ");
		sql.append(tableName + " WHERE ");
		sql.append(pkName + " = ?");
		// 执行SQL
		JdbcTemplate.update(sql.toString(), id);
	}

	public static void update(Long id, Object obj) {
	//	"UPDATE t_students SET name = ?, age = ? WHERE id = ?"	
		// 获取表名
		String tableName = getTableName(obj.getClass());
		String pkName = "id";  // 默认主键名为“id”
		// 拼接SQL中的"列名=？"部分，并获取每个？对应的值
		StringBuilder columnName = new StringBuilder();
		List<Object> list = new ArrayList<>();
		try {
			BeanInfo info = Introspector.getBeanInfo(obj.getClass(), Object.class);
			PropertyDescriptor[] pds = info.getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
            	Field field = obj.getClass().getDeclaredField(pd.getName());
				Id annId = field.getAnnotation(Id.class);
				if(annId == null && !"id".equals(field.getName())) {  // 表中的主键列不能修改
					Column annColumn = field.getAnnotation(Column.class);  // 判断一个字段是否被标注了Column注释
					if(annColumn != null) {  // 如果标注了就用注释的列名，否则用字段名作为列名
						columnName.append(annColumn.value() + " = ?,");
					} else {
						columnName.append(field.getName() + " = ?,");
					}
					list.add(pd.getReadMethod().invoke(obj));  // 获取与字段对应的值，放入集合中
				} else if(annId != null) {
					pkName = field.getName();  // 如果有注释，则获取表中的主键名
				}
			}
            columnName.deleteCharAt(columnName.length()-1);  // 去掉最后的逗号
            list.add(id);      // 将最后的过滤条件中的id值加入集合
			// 拼接SQL
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE " + tableName + " SET ");
            sql.append(columnName);
            sql.append(" WHERE " + pkName + " = ?");
			// 执行SQL
			JdbcTemplate.update(sql.toString(), list.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static <T> T get(Long id, Class<T> classType) {
		//获取表名
		String tableName = getTableName(classType);
		// 获取表的主键名
		String pkName = "id";
		Field[] fs = classType.getDeclaredFields();
		for (Field field : fs) {
			Id annId = field.getAnnotation(Id.class);
			if(annId != null) {
				pkName = field.getName();
			}
		}
		//拼SQL
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ");
		sql.append(tableName + " WHERE ");
		sql.append(pkName + " = ?");
		//使用模版工具类JdbcTemplate执行SQL，并使用增强版结果集处理器 EnhancedBeanHandler，最后返回一个封装后的对象
		// 增强版结果集处理器，可以自动处理对象的属性名与表的列名不一致的情况，前提是对象的字段上需要加Column注释，标注对应的列名
		return JdbcTemplate.query(sql.toString(), new EnhancedBeanHandler<T>(classType), id);
	}
	
	public static <T> List<T> listAll(Class<T> classType) {
		// 获取表名
		String tableName =  getTableName(classType);
		// 拼SQL
		String sql = "SELECT * FROM " + tableName;
		// 使用模版工具类JdbcTemplate 执行SQL，并使用结果集处理器 EnhancedBeanListHandler，最后返回一个包含所有对象的集合
		return JdbcTemplate.query(sql, new EnhancedBeanListHandler<T>(classType));
	}
	
}
