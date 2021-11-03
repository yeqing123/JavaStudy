package com.yeqing.mybatis.product.dao.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//封装了加载mybatis配置文件，获取SqlSession对象的方法
public class MybatisUtil {
	private static SqlSessionFactory factory;
	private static SqlSession session;
	static {
		try {
			InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//直接获取映射接口
	public static <T> T getMapper(Class<T> mapperClass) {
		session = factory.openSession();
		return session.getMapper(mapperClass);
	}
	//关闭SqlSession资源
	public static void close() {
		if(session != null) {
			session.close();
		}
	}
}
