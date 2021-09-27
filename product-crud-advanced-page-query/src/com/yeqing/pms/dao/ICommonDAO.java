package com.yeqing.pms.dao;

import com.yeqing.pms.page.PageResult;
import com.yeqing.pms.query.IQuery;


//通用的数据库DAO接口，用于规范数据库操作
public interface ICommonDAO {
	/**
	 * 增加数据
	 * @param obj 封装了要增加的信息
	 */
	void save(Object obj);
	/**
	 * 删除指定数据
	 * @param id 被删除数据的ID
	 */
	void delete(Long id);
	/**
	 * 修改指定的数据
	 * @param obj 被修改后的新信息
	 */
	void update(Object obj);
	/**
	 * 查询指定的数据
	 * @param id 被查找数据的ID
	 * @reObjecturn 返回封装了数据的javabean对象
	 */
	Object get(Long id);
	/**
	 * 查询符合条件的数据（可以实现高级查询和分页查询）
	 * @param qo 封装了查询条件的对象
	 * @reObjecturn 返回一个包含了查询结果集和分页的相关信息
	 */
	PageResult query(IQuery qo);
}
