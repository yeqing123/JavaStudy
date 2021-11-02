package com.yeqing.mybatis.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yeqing.mybatis.domain.Employee;
import com.yeqing.mybatis.mapper.EmployeeMapper;
import com.yeqing.mybatis.query.QueryObject;
import com.yeqing.mybatis.service.IQueryService;
import com.yeqing.mybatis.util.MybatisUtil;

//实现IQueryService接口，对employee表进行高级查询和分页查询
public class QueryServiceImpl implements IQueryService {
   //返回一个PageInfo对象
	public PageInfo<Employee> query(QueryObject qo) {
		SqlSession session = MybatisUtil.getSession();
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());  //使用PageHelper拦截器，设置分页查询
		List<Employee> result = mapper.advanceQuery(qo);
		return new PageInfo<>(result);
	}
}
