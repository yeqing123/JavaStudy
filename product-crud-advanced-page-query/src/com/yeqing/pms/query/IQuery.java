package com.yeqing.pms.query;

import java.util.List;

public interface IQuery {
    /**
     * 获取查询语句中所有参数值
     * @return  返回一个包含参数值的List集合
     */
	List<Object> getParameters();
	/**
	 * 创建SQL中的查询条件部分，形如：WHERE 条件1 AND 条件2...
	 * @return 返回一个SQL字符串
	 */
	String getQuery();
	/**
	 * 获取用户希望跳转的页码
	 * @return 返回一个整数值
	 */
	Integer getCurrentPage();
	/**
	 * 获取用户输入的每页显示的数据量
	 * @return 返回一个整数值
	 */
	Integer getPageSize();

}
