package com.yeqing.property_placeholder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.alibaba.druid.pool.DruidDataSource;

//需求：使用属性占位符来创建数据库连接池
@SpringJUnitConfig
public class App {

	private static DruidDataSource ds;
    static {
		ds = new DruidDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/springdemo");
		ds.setUsername("root");
		ds.setPassword("mysqladmin");
		ds.setInitialSize(2);
	}
	@Test
	void testOld() throws Exception {
		Connection con = ds.getConnection();
		PreparedStatement pst = con.prepareStatement("SELECT id,name,age FROM student");
		ResultSet rs = pst.executeQuery();
		List<Student> list = new ArrayList<>();
		while(rs.next()) {
			Student s = new Student();
			s.setId(rs.getLong("id"));
			s.setName(rs.getString("name"));
			s.setAge(rs.getInt("age"));
			list.add(s);
		}
		System.out.println(list);
	}

	@Autowired
	private DataSource spDs;
	@Test//使用Spring容器来帮助我们创建连接池对象，并自动注入连接属性
	void testIoC() throws Exception {
		Connection con = spDs.getConnection();
		PreparedStatement pst = con.prepareStatement("SELECT id,name,age FROM student");
		ResultSet rs = pst.executeQuery();
		List<Student> list = new ArrayList<>();
		while(rs.next()) {
			Student s = new Student();
			s.setId(rs.getLong("id"));
			s.setName(rs.getString("name"));
			s.setAge(rs.getInt("age"));
			list.add(s);
		}
		System.out.println(list);
	}
}
