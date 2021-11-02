package com.yeqing.mybatis.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//自定义一个Mybatis工具类，用于初始化SqlSessionFactory对象，从而可以获得一个SqlSession对象
public class MybatisUtil {
	private static SqlSessionFactory factory;
	//使用静态代码块，当工具类的字节码首次加载到JVM中时，该代码块会被一同加载到JVM中，此后就不再重复加载了
	static {
		try {
			InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//获取一个SqlSession对象
	public static SqlSession getSession() {
		return factory.openSession();
	}
}
