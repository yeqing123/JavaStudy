package com.yeqing.mybatis.service;

import com.yeqing.mybatis.query.PageResult;
import com.yeqing.mybatis.query.QueryObject;

/**
 * 合并了高级查询和分页查询功能，返回一个PageResult对象
 * @author yeqin
 *
 */
public interface IQueryService {
    
	PageResult query(QueryObject qo);
}
