package com.yeqing.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//获取SqlSession对象的工具类
public class MybatisUtil {
	private static SqlSessionFactory factory = null;
	static {
		InputStream in;
		try {
			in = Resources.getResourceAsStream("mybatis-config.xml");
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//获取一个Mapper实例
	public static <T> T getMapper(Class<T> classType) {
		return factory.openSession(true).getMapper(classType);
	}
}
