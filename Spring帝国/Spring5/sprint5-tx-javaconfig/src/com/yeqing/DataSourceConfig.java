package com.yeqing;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.druid.pool.DruidDataSource;

//数据库连接池的配置类
@Configuration
@PropertySource("classpath:db.properties")  //导入资源文件
public class DataSourceConfig {
    //连接数据的要素
	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;
	@Value("${jdbc.initialSize}")
	private int initialSize;
	
	@Bean   //使用该注解相当于在XML中使用<bean id="..." class="...">配置了一个Bean，而方法名就是bean元素的id
	public DataSource dataSource() {
		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName(driverClassName);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setInitialSize(initialSize);
		return ds;
	}
}
