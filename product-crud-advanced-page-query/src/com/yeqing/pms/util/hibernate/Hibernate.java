package com.yeqing.pms.util.hibernate;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yeqing.pms.page.PageResult;
import com.yeqing.pms.query.IQuery;
import com.yeqing.pms.util.ann.Column;
import com.yeqing.pms.util.ann.Id;
import com.yeqing.pms.util.ann.IdType;
import com.yeqing.pms.util.ann.Table;
import com.yeqing.pms.util.template.JdbcTemplate;
import com.yeqing.pms.util.template.handler.BeanHandler;
import com.yeqing.pms.util.template.handler.BeanListHandler;
import com.yeqing.pms.util.template.handler.IResultSetHandler;

public class Hibernate {
	public static void save(Class<?> classType, Object obj) {
		String tableName = getTableName(classType);
		StringBuilder headSql = new StringBuilder(80);
		StringBuilder tailSql = new StringBuilder(80);
		headSql.append("INSERT INTO ").append(tableName).append(" (");
		tailSql.append(" VALUES (");
		try {
			// 1:将要添加的javabean对象转换成一个Map集合
			Map<String, Object> map = javaBean2Map(classType, obj);
			// 2:遍历Map取出全部的key，拼接SQL，然后将每个value放入一个List集合中
			List<Object> params = new ArrayList<>();
			for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
				String key = it.next();
				headSql.append(key + ",");
				tailSql.append("?,");
				params.add(map.get(key));
			}
			headSql.delete(headSql.length() - 1, headSql.length()); // 删除headSql中最后一个多余的逗号
			tailSql.delete(tailSql.length() - 1, tailSql.length()); // 删除tailSql中最后一个多余的逗号
			headSql.append(")");
			tailSql.append(")");
			StringBuilder sql = headSql.append(tailSql); // 合并成一条完整的SQL语句
			System.out.println(sql); // 操作数据库之前打印一下SQL，便于查找错误
			System.out.println("参数：" + params);
			// 3：调用JdbcTemplate模板类，执行SQL
			JdbcTemplate.update(sql.toString(), params.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void delete(Class<?> classType, Long id) {
		String tableName = getTableName(classType);  //获取表名
		PrimaryColumn primary = getPrimaryColumn(classType, null);        //获取封装了主键属性的对象
		StringBuilder sql = new StringBuilder(80);
		sql.append("DELETE FROM ").append(tableName).append(" WHERE ").append(primary.getName()).append("=?");
		JdbcTemplate.update(sql.toString(), id);
	}

	public static void update(Class<?> classType, Object obj) {
		String tableName = getTableName(classType);
		PrimaryColumn primary = getPrimaryColumn(classType, obj);        //获取封装了主键属性的对象
		StringBuilder sql = new StringBuilder(80);
		sql.append("UPDATE ").append(tableName).append(" SET ");
		try {
			Map<String, Object> map = javaBean2Map(classType, obj); // 将javabean对象转换成一个Map
			List<Object> params = new ArrayList<>();
			for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) { // 遍历Map中的所有key集合，拼接SQL，并将所有value放入一个List集合
				String key = it.next();
				sql.append(key).append("=?,");
				params.add(map.get(key));
			}
			sql.delete(sql.length() - 1, sql.length()); // 删除sql中最后一个多余的逗号
			sql.append(" WHERE ").append(primary.getName()).append("=?");  //拼接SQL最后的条件，即指定要修改的数据
			params.add(primary.getValue());    //将主键列的值放入参数列表
			System.out.println(sql);
			System.out.println("参数：" + params);
			JdbcTemplate.update(sql.toString(), params.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	public static Object get(Class<?> classType, Long id) {
		String tableName = getTableName(classType);
		PrimaryColumn primary = getPrimaryColumn(classType, null);
		StringBuilder sql = new StringBuilder(80);
		sql.append("SELECT * FROM ").append(tableName).append(" WHERE ").append(primary.getName()).append("=?");
		return JdbcTemplate.query(sql.toString(), new BeanHandler<>(classType), id);
	}

	public static PageResult query(Class<?> classType, IQuery qo) {
		String tableName = getTableName(classType);
		//查询符合条件的数据总数
		StringBuilder countSql = new StringBuilder(80);
		countSql.append("SELECT COUNT(*) FROM ").append(tableName).append(qo.getQuery());
		System.out.println(countSql);
		Integer totalCount = JdbcTemplate.query(countSql.toString(), new IResultSetHandler<Long>() {
			public Long handler(ResultSet rs) throws Exception {
				if(rs.next()) {
					return rs.getLong(1);
				}
				return 0L;
			}
		}, qo.getParameters().toArray()).intValue();
		//--------------------------------------------------------------------------------
		//执行条件查询
		StringBuilder resultSql = new StringBuilder(80);
		resultSql.append("SELECT * FROM ").append(tableName).append(qo.getQuery()).append(" LIMIT ?,?");
		Integer currentPage = qo.getCurrentPage();
		Integer pageSize = qo.getPageSize();
		qo.getParameters().add((currentPage-1)*pageSize);
		qo.getParameters().add(pageSize);
		System.out.println(resultSql);
		System.out.println("参数：" + qo.getParameters());
		List<?> listData = JdbcTemplate.query(resultSql.toString(), new BeanListHandler<>(classType), qo.getParameters().toArray());
		return new PageResult(listData, currentPage, pageSize, totalCount);
	}

	// 找到类型中用Id注解标注属性，将它的名称和属性值封装到PrimaryColumn对象中
	private static PrimaryColumn getPrimaryColumn(Class<?> classType, Object obj) {
		try {
			// 根据内省机制逐个遍历每个属性，找到主键列，完成SQL拼接
			BeanInfo beanInfo = Introspector.getBeanInfo(classType, Object.class);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor pd : pds) {
				String name = pd.getName();  //主键名称
				Long value = null;           //主键的值
				Id idAnn = classType.getDeclaredField(name).getAnnotation(Id.class);
				if (idAnn != null) { // 只有domain类型的属性上标注的Id注解，我们才能知道它是主键列
					Column columnAnn = classType.getDeclaredField(name).getAnnotation(Column.class);
					if (columnAnn != null) {
						name = columnAnn.value(); // 如果使用了Column注解，就用该注解所给的列名，否则以属性名为列明
					}
					if(obj != null) {   //如果调用者传入了javabean对象，就获取主键的值
						value = (Long) pd.getReadMethod().invoke(obj);  //获取主键列的值
					}
					return new PrimaryColumn(name, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 获得表名
	private static String getTableName(Class<?> classType) {
		String tableName = classType.getSimpleName(); // 默认类名为表名
		Table ann = classType.getAnnotation(Table.class);
		if (ann != null) { // 如果使用了注解Table，就用注解所给的表名
			tableName = ann.value();
		}
		return tableName;
	}

	// 将一个JavaBean对象转换为一个除自动递增的主键列的Map集合，集合中key为列名，value为属性值
	private static Map<String, Object> javaBean2Map(Class<?> beanType, Object obj) {
		Map<String, Object> map = new HashMap<>();
		try {
			// 1:获得javabean的所有属性描述符
			BeanInfo beanInfo = Introspector.getBeanInfo(beanType, Object.class);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			// 2:遍历每个描述符，并将对应的名称和值放入Map中
			for (int i = 0; i < pds.length; i++) {
				String name = pds[i].getName(); // 获取属性名
				Id idAnn = beanType.getDeclaredField(name).getAnnotation(Id.class); // 获取该属性上的Id注解
				// 判断该属性上存在Id注解，并且其参数值为IdType.AUTO_INCREMENT
				if(idAnn != null && idAnn.value() == IdType.AUTO_INCREMENT) {  //如果条件成立，就直接忽略该属性
					continue; 
				} else {  //如果条件不成立，则将该属性的列名和属性值放入Map中
					Column columnAnn = beanType.getDeclaredField(name).getAnnotation(Column.class);
					if (columnAnn != null) { // 如果属性上存在Column注解，就以该注解参数为列名，否则默认使用属性名
						name = columnAnn.value();
					}
					Object param = pds[i].getReadMethod().invoke(obj); // 获取属性值
					map.put(name, param); // 将列名和属性值放入map中
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
