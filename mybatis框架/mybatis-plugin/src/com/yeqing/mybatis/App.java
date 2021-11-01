package com.yeqing.mybatis;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yeqing.mybatis.mapper.EmployeeMapper;
import com.yeqing.mybatis.util.MybatisUtil;

public class App {
	@Test//测试自定义的拦截器（插件）CamelCaseInterceptor
	public void testOnelevelCache() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		List<Map<String, Object>> list = mapper.listMap();
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
		session.close();
	}
}
