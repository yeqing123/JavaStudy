package com.yeqing.register.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.yeqing.register.dao.IUserDAO;
import com.yeqing.register.domain.User;

public class UserDAOImpl implements IUserDAO {

	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(User u) throws SQLException {
		Connection conn = dataSource.getConnection();
		PreparedStatement pst = conn.prepareStatement("INSERT INTO user (username, age) VALUES (?,?)");
		pst.setString(1, u.getUsername());
		pst.setInt(2, u.getAge());
		pst.executeUpdate();
		System.out.println("保存数据库操作");
	}

}
