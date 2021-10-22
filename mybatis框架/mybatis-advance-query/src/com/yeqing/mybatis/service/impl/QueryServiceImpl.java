package com.yeqing.mybatis.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yeqing.mybatis.domain.Employee;
import com.yeqing.mybatis.mapper.EmployeeMapper;
import com.yeqing.mybatis.query.PageResult;
import com.yeqing.mybatis.query.QueryObject;
import com.yeqing.mybatis.service.IQueryService;
import com.yeqing.mybatis.util.MybatisUtil;

//实现IQueryService接口，对employee表进行高级查询和分页查询
public class QueryServiceImpl implements IQueryService {
   //返回一个PageResult对象
	public PageResult query(QueryObject qo) {
		SqlSession session = MybatisUtil.getSession();
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		List<Employee> result = mapper.advanceQuery(qo);
		int totalCount = mapper.getCount(qo);
		return new PageResult(result, qo.getCurrentPage(), qo.getPageSize(), totalCount);
	}

}
