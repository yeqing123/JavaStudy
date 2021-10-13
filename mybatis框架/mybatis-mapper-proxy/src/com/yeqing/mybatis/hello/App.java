package com.yeqing.mybatis.hello;



import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import com.yeqing.mybatis.hello.domain.User;
import com.yeqing.mybatis.hello.mapper.UserMapper;
import com.yeqing.mybatis.hello.proxy.MyMapperProxy;
import com.yeqing.mybatis.hello.util.MybatisUtil;

import lombok.Cleanup;

public class App {
	
	@Test
	public void testMockMapperProxy() throws Exception {
		SqlSession session = null;
		try {
			MyMapperProxy<UserMapper> proxy = new MyMapperProxy<UserMapper>();
			session = MybatisUtil.getSession();
			proxy.setSession(session);
			proxy.setMapperClass(UserMapper.class);
			//创建UserMapper接口的代理类的实例
			UserMapper userMapper = proxy.getMapperObject();
			//class com.sun.proxy.$Proxy9
			System.out.println(userMapper);
			User u = userMapper.get(1L);
			System.out.println(u);
		}finally {
			session.close();
		}
	}
	@Test
	public void testGet() throws Exception {
		SqlSession session = null;
		try {
			session = MybatisUtil.getSession();
			//创建UserMapper接口的代理类的实例
			UserMapper userMapper = session.getMapper(UserMapper.class);
			//class com.sun.proxy.$Proxy9
			System.out.println(userMapper.getClass());
			User u = userMapper.get(1L);
			System.out.println(u);
		}finally {
			session.close();
		}
	}
	@Test
	public void testList() throws Exception {
		SqlSession session = null;
		try {
			session = MybatisUtil.getSession();
			//创建UserMapper接口的代理类的实例
			UserMapper userMapper = session.getMapper(UserMapper.class);
			List<User> list = userMapper.listAll();
			for (User user : list) {
				System.out.println(user);
			}
		}finally {
			session.close();
		}
	}
	@Test
	public void testInsert() throws Exception {
		SqlSession session = null;
		try {
			User u = new User();
			u.setName("钢铁侠");
			u.setSalary(new BigDecimal(900));
			System.out.println(u);
			session = MybatisUtil.getSession();
			//创建UserMapper接口的代理类的实例
			UserMapper userMapper = session.getMapper(UserMapper.class);
			userMapper.insert(u);
			session.commit();  //因为生成的SqlSession是非自动提交的，因此在执行增删改操作时，需要手动提交
			System.out.println(u);  //在SQL的XML映射文件中设置，可以自动获取新增数据的主键值
		}finally {
			session.close();
		}
	}
	@Test
	public void testDelete() throws Exception {
		@Cleanup
		SqlSession session = MybatisUtil.getSession();
		//创建UserMapper接口的代理类的实例
		UserMapper userMapper = session.getMapper(UserMapper.class);
		userMapper.delete(4L);
		session.commit();
	}
	@Test
	public void testUpdate() throws Exception {
		SqlSession session = null;
		try {
			User u = new User();
			u.setId(4L);
			u.setName("蜘蛛侠");
			u.setSalary(new BigDecimal(800));
			session = MybatisUtil.getSession();
			//创建UserMapper接口的代理类的实例
			UserMapper userMapper = session.getMapper(UserMapper.class);
			userMapper.update(u);
			session.commit();
		}finally {
			session.close();
		}
	}
}
