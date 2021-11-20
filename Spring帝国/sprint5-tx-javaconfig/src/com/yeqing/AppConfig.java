package com.yeqing;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


//当前项目的主配置，相当于XML中的applicationConfig.xml
@Configuration    //该注解表示这是一个配置类
@Import(DataSourceConfig.class)  //包含了其他配置类
@ComponentScan    //IoC注解解释器，默认扫描当前配置类所在的包及其子包中的所有类
@EnableTransactionManagement    //事务注解解析器
public class AppConfig {
	//创建事务管理的Bean
	@Bean
	public DataSourceTransactionManager txManager(DataSource ds) {
		return new DataSourceTransactionManager(ds);
	}
}
