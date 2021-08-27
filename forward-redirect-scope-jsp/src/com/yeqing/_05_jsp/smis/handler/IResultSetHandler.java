package com.yeqing._05_jsp.smis.handler;

import java.sql.ResultSet;

public interface IResultSetHandler<T> {
    
	/**
     * 处理结果集，然后将每一行信息封装成一个类型为T（泛型）的对象
     * @param rs 查询数据库返回的结果集
     * @return 返回一个封装了每一行信息的对象，如果有多个对象则返回一个集合，如果只有一个对象则直接返回该对象
     */
	T handler(ResultSet rs);
}
