package com.yeqing.mybatis;


import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yeqing.mybatis.domain.Employee;
import com.yeqing.mybatis.domain.EmployeeExample;
import com.yeqing.mybatis.domain.EmployeeExample.Criteria;
import com.yeqing.mybatis.domain.EmployeeKey;
import com.yeqing.mybatis.mapper.EmployeeMapper;
import com.yeqing.mybatis.util.MybatisUtil;

public class App {
	@Test//使用QBC（Query by Generator）查询表employee中的指定员工
	public void testGet() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		EmployeeKey key = new EmployeeKey();
		key.setId(1L);
		Employee e = mapper.selectByPrimaryKey(key);
		System.out.println(e);
		session.close();
	}
	@Test//查询工资在800到1000之间，并且姓名中带有“周”字的员工
	public void testSelectByExample() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria(); //创建一个表示查询条件的对象
		//加入查询条件
		criteria = criteria.andNameLike("%周%"); 
		criteria.andSalaryBetween(new BigDecimal(800), new BigDecimal(1000));
		//使用selectByExample方法进行查询
		List<Employee> emps = mapper.selectByExample(example);
		System.out.println(emps);
		session.close();
	}
}
