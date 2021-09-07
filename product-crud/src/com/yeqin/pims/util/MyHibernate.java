package com.yeqin.pims.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

import com.yeqin.pims.util.ann.ColumnName;
import com.yeqin.pims.util.ann.Id;
import com.yeqin.pims.util.ann.IdType;
import com.yeqin.pims.util.ann.TableName;
import com.yeqin.pims.util.handler.BeanHandler;
import com.yeqin.pims.util.handler.BeanListHandler;

//模拟Hibernate框架，自动拼接SQL语句，并自动完成数据库操作的工具类
public class MyHibernate<T> {
    private Class<T> beanClass;
    public MyHibernate(Class<T> beanClass) {
    	this.beanClass = beanClass;
    }
    
    public void save(Object obj) {
    	//获取表名
    	String tableName = this.getTableName();
    	//获取与属性对应的，将被编入SQL语句中的所有列名和属性值，各放入一个List集合中
    	List<String> names = new ArrayList<>(); //保存列名
    	List<Object> values = new ArrayList<>(); //保存属性值
    	try {
			BeanInfo beanInfo = Introspector.getBeanInfo(beanClass, Object.class);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors(); //获取所有的属性值描述符
			for (PropertyDescriptor pd : pds) {
				String name = pd.getName();  //属性名
				Object value = pd.getReadMethod().invoke(obj); //属性值
				ColumnName columnAnn = beanClass.getDeclaredField(name).getAnnotation(ColumnName.class);
				Id IdAnn = beanClass.getDeclaredField(name).getAnnotation(Id.class);
				//非自动递增的主键列和普通的列，它的列名和对应的属性值，将分别放入集合中
				if(
						(IdAnn == null && !"id".equals(name)) ||      //如果没有设置Id注解，并且属性名不为“id”，则视为普通列
						(IdAnn != null && IdAnn.value()==IdType.NON_AUTO_INCREMENT)) { //如果设置了Id注解，并且注解参数值为“IdType.NON_AUTO_INCREMENT”，则视为非自动递增的主键列
					if(columnAnn != null) {  //有ColumnName注解，使用指定的列名
						name = columnAnn.value();
					}
					names.add(name); //将需要的列名加入集合
					values.add(value); //将需要属性值加入集合
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	//拼SQL
    	StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");
    	for (String name : names) {
			sql.append(name + ",");
		}
    	sql.deleteCharAt(sql.length()-1); //去掉最后一个多余的,
    	sql.append(") VALUES (");
    	for(int i = 0; i < names.size(); i++) {
    		sql.append("?,");
    	}
    	sql.deleteCharAt(sql.length()-1); //去掉最后一个多余的,
    	sql.append(")");
    	//执行SQL
    	JdbcTemplate.update(sql.toString(), values.toArray());
    }
    
    public void delete(Object id) {
    	//获取表名
    	String tableName = this.getTableName();
    	//获取主键列的名称
    	String idName = "";
    	try {
			BeanInfo beanInfo = Introspector.getBeanInfo(beanClass, Object.class);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors(); //获取所有的属性值描述符
			for (PropertyDescriptor pd : pds) {
				String name = pd.getName();  //属性名
				Id idAnn = beanClass.getDeclaredField(name).getAnnotation(Id.class);
				//判断该属性对应的列是否为主键列
				if(idAnn != null || (idAnn == null && "id".equals(name))) {
					ColumnName columnAnn = beanClass.getDeclaredField(name).getAnnotation(ColumnName.class);
					idName = name;
					if(columnAnn != null) {  //有ColumnName注解，使用指定的列名
						idName = columnAnn.value();
					}
					break; //找到主键列，就结束循环
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	//拼SQL
    	StringBuilder sql = new StringBuilder();
    	sql.append("DELETE FROM ")
    	   .append(tableName + " WHERE ")
    	   .append(idName + "=?");
    	//执行SQL
    	JdbcTemplate.update(sql.toString(), id);
    }
    
    public void update(Object obj) {
    	//1：获取表名
    	String tableName = this.getTableName();
    	//2：使用内省机制获取对象所有的属性名和属性值（包括主键列），放入将列名和属性值各放入一个List集合中
    	List<String> names = new ArrayList<>(); //保存列名
    	List<Object> values = new ArrayList<>(); //保存属性值
    	String idName = null;  //主键列名
		Object idValue = null; //主键对应的属性值
    	try {
			BeanInfo beanInfo = Introspector.getBeanInfo(beanClass, Object.class);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors(); //获取所有的属性值描述符
			for (PropertyDescriptor pd : pds) {
				String name = pd.getName();  //属性名
				Object value = pd.getReadMethod().invoke(obj); //属性值
				ColumnName columnAnn = beanClass.getDeclaredField(name).getAnnotation(ColumnName.class);
				Id idAnn = beanClass.getDeclaredField(name).getAnnotation(Id.class);
				//找出主键列，另存它的列名和属性值
				if(idAnn != null || (idAnn == null && "id".equals(name))) {  //是主键列
					if(columnAnn != null) {  //有ColumnName注解，使用指定的列名
						idName = columnAnn.value();
					}else {  //无ColumnName注解，用属性名作为列名
						idName = name;
					}
					idValue = pd.getReadMethod().invoke(obj);
				} else {   //不是主键列
					names.add(name); //将需要的列名加入集合
					values.add(value); //将需要属性值加入集合
				}
			}
			//将主键列的值保存在集合的末尾
	        values.add(idValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	//3：拼SQL
    	StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");
        for (String name : names) {
			sql.append(name + "=?,");
		}
        sql.deleteCharAt(sql.length()-1); //去掉最后一个多余的,
        sql.append(" WHERE ").append(idName).append("=?"); //主键列名拼在最后
    	//4：执行SQL
        JdbcTemplate.update(sql.toString(), values.toArray());
    }
    
    public T get(Object id) {
    	//获取表名
    	String tableName = this.getTableName();
    	//获取主键列名
    	//获取主键列的名称
    	String idName = "";
    	try {
			BeanInfo beanInfo = Introspector.getBeanInfo(beanClass, Object.class);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors(); //获取所有的属性值描述符
			for (PropertyDescriptor pd : pds) {
				String name = pd.getName();  //属性名
				Id idAnn = beanClass.getDeclaredField(name).getAnnotation(Id.class);
				//判断该属性对应的列是否为主键列
				if(idAnn != null || (idAnn == null && "id".equals(name))) {
					ColumnName columnAnn = beanClass.getDeclaredField(name).getAnnotation(ColumnName.class);
					idName = name;
					if(columnAnn != null) {  //有ColumnName注解，使用指定的列名
						idName = columnAnn.value();
					}
					break; //找到主键列，就结束循环
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	//拼SQL
    	StringBuilder sql = new StringBuilder();
    	sql.append("SELECT * FROM ").append(tableName).append(" WHERE ").append(idName).append("=?");
    	//执行SQL
    	return JdbcTemplate.query(sql.toString(), new BeanHandler<T>(this.beanClass), id);
    }
    
    public List<T> getAll() {
    	return JdbcTemplate.query("SELECT * FROM " + this.getTableName(), new BeanListHandler<T>(this.beanClass));
    }
    //获取表名
    private String getTableName() {
    	String tableName = beanClass.getSimpleName();  //默认以JavaBean的类名作为表名
    	TableName beanAnn = beanClass.getAnnotation(TableName.class); //获取类型上的TableName注解
    	if(beanAnn != null) { //有注解，用注解所给参数作为表名
    		tableName = beanAnn.value();
    	}
    	return tableName;
    }
}
