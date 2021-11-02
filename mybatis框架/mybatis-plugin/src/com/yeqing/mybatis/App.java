package com.yeqing.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import com.yeqing.mybatis.mapper.EmployeeMapper;
import com.yeqing.mybatis.util.MybatisUtil;

public class App {
	@Test//测试自定义的拦截器（插件）CamelCaseInterceptor
	public void testOnelevelCache() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		//查询表中的所有员工信息，得到一个List集合，集合中的每个元素保存了一个员工信息的Map，
		List<Map<String, Object>> list = mapper.listMap();
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
		session.close();
	}
}
