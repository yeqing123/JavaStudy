package com.yeqing.mybatis.hello;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import com.yeqing.mybatis.hello.util.MybatisUtil;


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
}
