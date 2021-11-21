package com.yeqing.ioc.register.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeqing.ioc.register.dao.IUserDAO;
import com.yeqing.ioc.register.domain.User;


@Repository
public class UserDAOImpl implements IUserDAO {

	@Autowired
	private DataSource dataSource;

	@Override
	public void save(User u) {
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement pst = conn.prepareStatement("INSERT INTO user (username,age) VALUES (?,?)");
			pst.setString(1, u.getUsername());
			pst.setInt(2, u.getAge());
			pst.executeUpdate();
			System.out.println("数据库保存操作");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
