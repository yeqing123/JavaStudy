package com.yeqing.mybatis;


import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yeqing.mybatis.domain.Teacher;
import com.yeqing.mybatis.mapper.TeacherMapper;
import com.yeqing.mybatis.util.MybatisUtil;

public class App {
	@Test//测试mybatis自带的一级缓存
	public void testOnelevelCache() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		TeacherMapper mapper = session.getMapper(TeacherMapper.class);
		Teacher t1 = mapper.get(1L);
		System.out.println(t1);
		System.out.println("----------------------------");
		//一级缓存的作用域是SqlSession级别的，不同的SqlSession对象之间不能共享缓存数据
		//如果关闭上一个SqlSession对象重新生成一个，就不发在使用上一个SqlSession对象的缓存了
		Teacher t2 = mapper.get(1L);
		System.out.println(t2);
		session.close();
	}
	@Test//测试mybatis自带的二级缓存（二级缓存进行手动的配置）
	public void testTwolevelCache() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		TeacherMapper mapper = session.getMapper(TeacherMapper.class);
		Teacher t1 = mapper.get(1L);
		System.out.println(t1);
		session.close();
		System.out.println("----------------------------");
		//二级缓存的作用域是mapper级别的，不同的SqlSession对象之间可以共享缓存数据
		session = MybatisUtil.getSession();
		mapper = session.getMapper(TeacherMapper.class);
		Teacher t2 = mapper.get(1L);
		System.out.println(t2);
		session.close();
	}
	@Test//测试第三方提供的Ehcache缓存，性能优于mybatis自带的缓存
	public void testEhcache() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		TeacherMapper mapper = session.getMapper(TeacherMapper.class);
		Teacher t1 = mapper.get(1L);
		System.out.println(t1);
		session.close();
		System.out.println("----------------------------");
		//为了验证使用的是Ehcache，让程序睡3秒再进行第二次查询，并在Ehcache配置文件中设置缓存数据最大空闲时间为3秒，就是说等程序睡醒了缓存也就被清除了
		Thread.sleep(3000);  
		//Ehcache的作用域也是mapper级别的
		session = MybatisUtil.getSession();
		mapper = session.getMapper(TeacherMapper.class);
		Teacher t2 = mapper.get(1L);
		System.out.println(t2);
		session.close();
	}
}
