package com.yeqing.sims.util.handler;

import java.sql.ResultSet;

//结果集处理器
public interface IResultSetHandler<T> {
	/**
	 * 处理结果集
	 * @param rs 查询数据库后得到的结果集对象
	 * @return 返回处理后的结果，如果查询的是一个指定信息，则返回一个指定类型的JavaBean对象；如果查询的是所有信息，则返回一个包含指定类型对象的List集合
	 */
    T handler(ResultSet rs);
}
