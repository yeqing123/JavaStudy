package com.yeqing.tx;


import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import com.yeqing.exercise.util.JdbcUtil;

public class TxTest {
	// 不使用事务
	@Test
    public void test1() {
    	Connection conn = JdbcUtil.getConn();
    	PreparedStatement ps = null;
    	try {
	    	// ===============将张无忌的存款余额减少1000元===========
			String sql = "UPDATE account SET balance=balance-1000 WHERE id=1";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			JdbcUtil.close(null, ps, null);
			//===============将赵敏的存款增加1000元===============
			// 模拟停电
			int a = 1 / 0;
			sql = "UPDATE account SET balance=balance+1000 WHERE id=2";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
		    JdbcUtil.close(conn, ps, null);
    	}
	}
	//使用事务
	@Test
	public void test2() throws Exception {
		Connection conn = JdbcUtil.getConn();
		conn.setAutoCommit(false);  // 设置为手动提交
    	PreparedStatement ps = null;
    	try {
	    	// ===============将张无忌的存款余额减少1000元===========
			String sql = "UPDATE account SET balance=balance-1000 WHERE id=1";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			JdbcUtil.close(null, ps, null);
			//===============将赵敏的存款增加1000元===============
			// 模拟停电
			int a = 1 / 0;
			sql = "UPDATE account SET balance=balance+1000 WHERE id=2";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			conn.commit();  // 手动提交
    	} catch(Exception e) {
    		conn.rollback(); // 回滚
    		e.printStackTrace();
    	} finally {
		    JdbcUtil.close(conn, ps, null);
    	}
	}
}
