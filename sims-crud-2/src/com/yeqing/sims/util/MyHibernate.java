package com.yeqing.sims.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yeqing.sims.ann.Column;
import com.yeqing.sims.ann.Id;
import com.yeqing.sims.util.handler.BeanResultSetHandler;
import com.yeqing.sims.util.handler.ListResultSetHandler;

/**
 * 模拟框架Hibernate，用于简化对数据库的操作；该工具类可以根据JavaBean的类型或调用者给出的表的名称，
 * 自动生成相应的SQL语句并执行操作。从而实现对数据库的全自动化操作，用户则不用再自己拼写SQL语句。
 * 
 * @author yeqin
 *
 */
public class MyHibernate {
	private String tableName = null;
    private Template tem = null;
	public MyHibernate(String tableName) {
		this.tableName = tableName;
		this.tem = new Template(this.tableName);
	}

	/**
	 * 向数据库中添加信息
	 * 
	 * @param obj 新添加信息的bean对象
	 */
	public void save(Object obj) {
		tem.saveOrUpdate(obj, OperateType.SAVE);
	}

	/**
	 * 删除指定的信息
	 * 
	 * @param id 指定信息的ID
	 * @param beanType 用于查找对象中的属性名
	 */
	public void delete(Long id, Class<?> beanType) {
		tem.deleteOrGet(id, beanType, OperateType.DELETE);
	}

	/**
	 * 修改指定数据的信息
	 * 
	 * @param obj 封装了新信息的bean对象
	 */
	public void update(Object obj) {
		tem.saveOrUpdate(obj, OperateType.UPDATE);
	}

	/**
	 * 查询指定的信息
	 * 
	 * @param <T>      泛指任意的bean类型
	 * @param id       指定信息的ID
	 * @param beanType 用于封装查询到的信息的对象类型
	 * @return 返回封装了结果信息的JavaBean对象
	 */
	public <T> T get(Long id, Class<T> beanType) {
		return tem.deleteOrGet(id, beanType, OperateType.QUERY_ONE);
	}

	/**
	 * 查询数据库中的所有信息
	 * 
	 * @param <T>      泛指任意的bean类型
	 * @param beanType 封装信息的对象类型
	 * @return 返回保存了所有信息对象的List集合
	 */
	public <T> List<T> getAll(Class<T> beanType) {
		return tem.getAll(beanType);
	}
}

//==========================================================================
//定义枚举类，成员表示“增、删、改、查”，四种操作类型
enum OperateType {
	SAVE, DELETE, UPDATE, QUERY_ONE
}

//供MyHibernate中使用的数据库操作的模板类
class Template {
	private String tableName;
	Template(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * 实现对数据库“增删改”的模版方法
	 * 
	 * @param tableName 要操作的数据库表的名称
	 * @param param     操作数据库需要的对象参数，如果是添加或修改操作，则是一个JavaBean对象；如果是删除操作，则是被删除信息的ID
	 * @param flag      一个自定义的枚举类型参数，用于标识应该进行什么类型的操作。它一共有SAVE,DELETE,UPDATE三个元素，分别表示“增删改”三种操作类型
	 */
	public void saveOrUpdate(Object obj, OperateType flag) {
		if (!this.hasLength(tableName)) {
			throw new RuntimeException("表名为空，请在构造MyHibernate对象时传入要操作的数据库表的名称！");
		}
		try {
			StringBuilder sql = null;
			String idName = ""; // 暂时保存主键名
			Object idValue = null;// 暂时保存主键值
			if (flag == OperateType.UPDATE) {
				sql = new StringBuilder("UPDATE " + tableName + " SET ");
			} else if(flag == OperateType.SAVE) {
				sql = new StringBuilder("INSERT INTO " + tableName + " (");
			}
			List<Object> values = new ArrayList<>();
			//调用bean2map()方法，获得一个Map集合
			Map<String, Object> map = this.bean2map(obj);
			// 遍历Map集合，拼接SQL语句
			for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext();) {
				String name = iter.next(); // 获取属性名
				Object value = map.get(name); // 获取属性值
				Field f = obj.getClass().getDeclaredField(name); // 获得与属性名对应Field对象
				Column columnAnn = f.getAnnotation(Column.class); // 判断属性上是否贴了Column注解
				Id idAnn = f.getAnnotation(Id.class); // 判断是否贴了Id注解
				if (columnAnn != null) { // 有Column注解，则使用注解所给的列名
					name = columnAnn.value();
				}
				if (idAnn == null) { // 不是主键，则直接拼入SQL
					if (flag == OperateType.UPDATE) {
						sql.append(name + "=?,");
					} else {
						sql.append(name + ",");
					}
					values.add(value);
				} else { // 是主键，则先另外保存列名和属性值
					idName = name;
					idValue = value;
				}
			}
			sql.deleteCharAt(sql.length() - 1); //去掉最后多余的,
			//完成SQL剩余部分的拼接
			if (flag == OperateType.UPDATE) {  // 修改
				values.add(idValue); // 主键值放入list的集合的末尾
				sql.append(" WHERE " + idName + "=?");
			} else {  // 新增
				sql.append(") VALUES (");
				for (int i = 0; i < values.size(); i++) {
					sql.append("?,");
				}
				sql.deleteCharAt(sql.length() - 1);
				sql.append(")");
			}
			//使用JdbcTemplate执行SQL
			JdbcTemplate.update(sql.toString(), values.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 根据flag参数的标识，删除或查询指定的信息
	public <T> T deleteOrGet(Long id, Class<T> beanType, OperateType flag) {
		if (!this.hasLength(tableName)) {
			throw new RuntimeException("表名为空，请在构造MyHibernate对象时传入要操作的数据库表的名称！");
		}
		StringBuilder sql = null;
		if(flag == OperateType.QUERY_ONE) {
			sql = new StringBuilder("SELECT * FROM " + tableName + " WHERE ");
		} else if(flag == OperateType.DELETE) {
			sql = new StringBuilder("DELETE FROM " + tableName + " WHERE ");
		}
		try {
			Object obj = beanType.newInstance();
			// 调用bean2map()方法，获得一个Map集合
			Map<String, Object> map = this.bean2map(obj);
			int count = map.size();
			for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext();) {
				String name = iter.next(); // 获取属性名
				Field f = obj.getClass().getDeclaredField(name); // 获得与属性名对应Field对象
				Column columnAnn = f.getAnnotation(Column.class); // 判断属性上是否贴了Column注解
				Id idAnn = f.getAnnotation(Id.class); // 判断是否贴了Id注解
				if (columnAnn != null) { // 有Column注解，则使用注解所给的列名
					name = columnAnn.value();
				}
				if (idAnn != null) { // 不是主键，则将列名拼入SQL并执行
					sql.append(name + "=?");
					break;  // 注意，不要忘了结束for循环
				}
				count--;
			}
			if(count == 0) {  //如果count等于0，说明for循环已经遍历结束，也没有找到标记了Id注解的属性，那就默认用字符串id作为主键的属性名
			    sql.append("id=?");
			}
			if(flag == OperateType.QUERY_ONE) {
				return JdbcTemplate.query(sql.toString(), new BeanResultSetHandler<T>(beanType), id);
			} else if(flag == OperateType.DELETE) {
				JdbcTemplate.update(sql.toString(), id);
				return null;  //终止for循环
			}
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
		return null;
	}
	//查询所有信息
	public <T> List<T> getAll(Class<T> beanType) {
		if (!this.hasLength(tableName)) {
			throw new RuntimeException("表名为空，请在构造MyHibernate对象时传入要操作的数据库表的名称！");
		}
		String sql = "SELECT * FROM " + tableName;
		return JdbcTemplate.query(sql, new ListResultSetHandler<T>(beanType));
	}

	// 将一个javabean转换成一个Map集合
	private Map<String, Object> bean2map(Object obj) throws Exception {
		Map<String, Object> map = new HashMap<>();
		// 拿到对象的Class对象
		Class<?> beanType = obj.getClass();
		// 使用JavaBean内省机制，拿到BeanInfo
		BeanInfo beanInfo = Introspector.getBeanInfo(beanType, Object.class);
		// 从BeanInfo中获得所有属性的描述器
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		// 遍历每一个属性描述器，获取它们的属性名和属性值，保存到map集合中
		for (PropertyDescriptor pd : pds) {
			String name = pd.getName(); // 获取属性名
			Object value = pd.getReadMethod().invoke(obj); // 获取属性值
			map.put(name, value); // 放入Map集合中
		}
		return map;
	}

	// 判断字符串是否为空
	public boolean hasLength(String str) {
		return str != null && !"".equals(str);
	}
}

