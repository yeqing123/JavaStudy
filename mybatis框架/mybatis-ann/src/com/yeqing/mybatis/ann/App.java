package com.yeqing.mybatis.ann;



import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yeqing.mybatis.ann.domain.User;
import com.yeqing.mybatis.ann.mapper.UserMapper;
import com.yeqing.mybatis.ann.util.MybatisUtil;

import lombok.Cleanup;
/*
 * 在使用Mybatis开发过程中，有两种方式来实现关系映射：
 * 方式一：使用XML映射文件；
 * 方式二：直接在映射接口XxxMapper的操作方法上使用注解进行开发。主要的注解有：@Insert、@Delete、@Update、@Select
 * 推荐使用XML文件，因为看起来更清晰，直接。
 * 
 * 本项目就是使用注解开发的实例，注意使用注解后就没有XML映射文件了只有映射接口，所以在总的XML配置文件中，配置映射器是要使用<mapper class="映射接口的全限定名"/>来关联到映射接口。
 */
public class App {
	@Test
	public void testGet() throws Exception {
		SqlSession session = null;
		try {
			session = MybatisUtil.getSession();
			UserMapper userMapper = session.getMapper(UserMapper.class);
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
		User u = new User();
		u.setName("钢铁侠");
		u.setSalary(new BigDecimal(900));
		System.out.println(u);
		SqlSession session = null;
		try {
			session = MybatisUtil.getSession();
			UserMapper userMapper = session.getMapper(UserMapper.class);
		    userMapper.insert(u);
			session.commit();  //因为生成的SqlSession是非自动提交的，因此在执行增删改操作时，需要手动提交
			System.out.println(u);  //在UpserMapper接口中使用@Options注解进行设置，自动获取新增数据的主键值
		}finally {
			session.close();
		}
	}
	@Test
	public void testDelete() throws Exception {
		@Cleanup
		SqlSession session = MybatisUtil.getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		userMapper.delete(7L);
		session.commit();
	}
	@Test
	public void testUpdate() throws Exception {
		User u = new User();
		u.setId(5L);
		u.setName("绿巨人");
		u.setSalary(new BigDecimal(700));
		SqlSession session = null;
		try {
			session = MybatisUtil.getSession();
			UserMapper userMapper = session.getMapper(UserMapper.class);
			userMapper.update(u);
			session.commit();
		}finally {
			session.close();
		}
	}
}
