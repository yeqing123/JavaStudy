package com.yeqing.mybatis.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//加载配置文件，然后获取SqlSession对象的工具类
public class MybatisUtil {
	private static SqlSessionFactory factory;
	//将下面的代码放入静态代码块中是为了避免重复加载
	static {  
		try {
			InputStream in = Resources.getResourceAsStream("mybatis-config.xml");  //加载配置文件
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	public static SqlSession getSession() {
		return factory.openSession();
	}
}
