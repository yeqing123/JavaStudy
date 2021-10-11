package com.yeqing.mybatis.hello;



import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yeqing.mybatis.hello.util.MybatisUtil;

import lombok.Cleanup;

public class App {
	@Test
	public void testGet() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		User u = session.selectOne("com.yeqing.mybatis.hello.UserMapper.get", 1L);
		session.close();
		System.out.println(u);
	}
	@Test
	public void testList() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		List<User> list = session.selectList("com.yeqing.mybatis.hello.UserMapper.listAll");
		session.close();
		for (User user : list) {
			System.out.println(user);
		}
	}
	@Test
	public void testInsert() throws Exception {
		User u = new User();
		u.setName("钢铁侠");
		u.setSalary(new BigDecimal(900));
		System.out.println(u);
		SqlSession session = MybatisUtil.getSession();
		session.insert("com.yeqing.mybatis.hello.UserMapper.insert", u);
		session.commit();  //因为生成的SqlSession是非自动提交的，因此在执行增删改操作时，需要手动提交
		session.close();
		System.out.println(u);  //在SQL的XML映射文件中设置，可以自动获取新增数据的主键值
	}
	@Test
	public void testDelete() throws Exception {
		@Cleanup
		SqlSession session = MybatisUtil.getSession();
		session.delete("com.yeqing.mybatis.hello.UserMapper.delete", 11L);
		session.commit();
	}
	@Test
	public void testUpdate() throws Exception {
		User u = new User();
		u.setId(7L);
		u.setName("奇异博士");
		u.setSalary(new BigDecimal(1000));
		SqlSession session = MybatisUtil.getSession();
		session.update("com.yeqing.mybatis.hello.UserMapper.update", u);
		session.commit();
		session.close();
	}
	@Test
	public void testCount() throws Exception {
		SqlSession session = null;
		try {
			session = MybatisUtil.getSession();
			int count = session.selectOne("com.yeqing.mybatis.hello.UserMapper.queryCount");
			System.out.println("数据库mybatisdemo中一共有" + count + "条数据");
		} finally {
			session.close();
		}
	}
	@Test
	public void testQueryMap() throws Exception {
		SqlSession session = null;
		try {
			session = MybatisUtil.getSession();
			List<Map<String,Object>> list  = session.selectList("com.yeqing.mybatis.hello.UserMapper.queryMap");
			for (Map<String, Object> map : list) {
				System.out.println(map);
			}
		} finally {
			session.close();
		}
	}
}
