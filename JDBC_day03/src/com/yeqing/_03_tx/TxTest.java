package com.yeqing._03_tx;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import util.JdbcUtil;

//事务测试
public class TxTest {
    @Test
	public void test1() throws Exception {
    	Connection conn = JdbcUtil.getConnection();
		//----------------检查张无忌的账户余额是否大于1000--------------------
    	String sql = "SELECT * FROM account WHERE name = ? AND balance >= ?";
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setString(1, "张无忌");
    	ps.setInt(2, 1000);
    	ResultSet rs = ps.executeQuery();
    	if(!rs.next()) {
    		throw new RuntimeException("余额不足");
    	}
    	//---------------张无忌余额减少1000-----------------------------------
    	sql = "UPDATE account SET balance = balance - ? WHERE name = ?";
    	ps = conn.prepareStatement(sql);
    	ps.setInt(1, 1000);
    	ps.setString(2, "张无忌");
    	ps.executeUpdate();
    	//使用异常，模拟停电
    	int a = 1 / 0;
    	//--------------赵敏余额增加1000--------------------------------------
    	sql = "UPDATE account SET balance = balance + ? WHERE name = ?";
    	ps = conn.prepareStatement(sql);
    	ps.setInt(1, 1000);
    	ps.setString(2, "赵敏");
    	ps.executeUpdate();
    	JdbcUtil.close(conn, ps, rs);
	}
    //处理事务
    @Test
	public void test2() {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//----------------检查张无忌的账户余额是否大于1000--------------------
	    	String sql = "SELECT * FROM account WHERE name = ? AND balance >= ?";
	    	ps = conn.prepareStatement(sql);
	    	ps.setString(1, "张无忌");
	    	ps.setInt(2, 1000);
	    	rs = ps.executeQuery();
	    	if(!rs.next()) {
	    		throw new RuntimeException("余额不足");
	    	}
	    	//---------------张无忌余额减少1000-----------------------------------
	    	// 设置事务的手动提交
	    	conn.setAutoCommit(false);
	    	sql = "UPDATE account SET balance = balance - ? WHERE name = ?";
	    	ps = conn.prepareStatement(sql);
	    	ps.setInt(1, 1000);
	    	ps.setString(2, "张无忌");
	    	ps.executeUpdate();
	    	//使用异常，模拟停电
	    	int a = 1 / 0;
	    	//--------------赵敏余额增加1000--------------------------------------
	    	sql = "UPDATE account SET balance = balance + ? WHERE name = ?";
	    	ps = conn.prepareStatement(sql);
	    	ps.setInt(1, 1000);
	    	ps.setString(2, "赵敏");
	    	ps.executeUpdate();		
	    	conn.commit();   // 提交事务
		}catch(Exception e) {
			e.printStackTrace();
			try {
                conn.rollback();  // 回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
		}finally {
			JdbcUtil.close(conn, ps, rs);
		}
	}
}
