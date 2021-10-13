package com.yeqing.mybatis.params;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yeqing.mybatis.params.domain.Client;
import com.yeqing.mybatis.params.domain.LoginVO;
import com.yeqing.mybatis.params.mapper.ClientMapper;
import com.yeqing.mybatis.params.util.MybatisUtil;

public class App {
	/*
	 * 使用SqlSession对数据库进行操作时，我们只能插入一个参数，所以无法进行比如多条件查询这类的操作。有三种解决办法：
	 * 方式一：自定义一个POJO（Java普通对象）将所有的参数封装起来，但是该方法缺点在于当执行的操作很多时，也要定义很多的类型来封装参数。
	 * 方式二：将所有参数放入一个Map集合中，该方式免去的定义对象的麻烦。Map集合的key就是属性名，value就是属性值。
	 * 方式三：也是最好的办法，就是使用Mybatis提供的@Param注解，只要在每个参数前面加上该注解即可。注解中的值就是对相应的属性名。
	 * 其实@Param注解底层还是用的Map集合，只不过Mybatis帮我们实现了集合定义的操作。
	 */
	//下面我们模拟登录操作来逐一验证上述三种方法：
	@Test//方式一
	public void testLogin1() throws Exception {
		SqlSession session = null;
		try {
			session = MybatisUtil.getSession();
			ClientMapper clientMapper = session.getMapper(ClientMapper.class);
			Client c = clientMapper.login1(new LoginVO("yeqing", "1234"));
			System.out.println(c);
		}finally {
			session.close();
		}
	}
	@Test//方式二
	public void testLogin2() throws Exception {
		SqlSession session = null;
		try {
			session = MybatisUtil.getSession();
			ClientMapper clientMapper = session.getMapper(ClientMapper.class);
			Client c = clientMapper.login2(new HashMap<String,Object>(){{
				this.put("username", "liusha");
				this.put("password", 1111);
			}});
			System.out.println(c);
		}finally {
			session.close();
		}
	}
	@Test//方式三
	public void testLogin3() throws Exception {
		SqlSession session = null;
		try {
			session = MybatisUtil.getSession();
			ClientMapper clientMapper = session.getMapper(ClientMapper.class);
			Client c = clientMapper.login3("yeqing", "1234");
			System.out.println(c);
		}finally {
			session.close();
		}
	}
	
	@Test
	public void testListAll() throws Exception {
		SqlSession session = null;
		try {
			session = MybatisUtil.getSession();
			ClientMapper clientMapper = session.getMapper(ClientMapper.class);
			List<Client> list = clientMapper.listAll("id DESC");
			for (Client c : list) {
				System.out.println(c);
			}
		}finally {
			session.close();
		}
	}
}
