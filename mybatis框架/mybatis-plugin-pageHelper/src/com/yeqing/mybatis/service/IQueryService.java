package com.yeqing.mybatis.service;

import com.github.pagehelper.PageInfo;
import com.yeqing.mybatis.query.QueryObject;

/**
 * 合并了高级查询和分页查询功能，返回一个PageInfo对象
 * @author yeqin
 *
 */
public interface IQueryService {
    //返回PageHelper中的PageInfo对象，它包含了分页的信息，相当于我们自定义的PageResult
	PageInfo<?> query(QueryObject qo);
}
