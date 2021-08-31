package com.yeqing.sims.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.yeqing.sims.util.handler.IResultSetHandler;

/**
 * 用于完成数据库操作基本流程（贾琏欲执事）的模板类。
 * 对于数据库的“增删改查”操作，可以分为两类：
 * 一类没有返回结果（“增删改”操作），一类有返回结果（查询操作）。模板类中在update()静态方法中实现了“增删改”操作的基本流程，
 * 在query()静态方法中实现了查询操作的基本流程。
 * @author yeqing
 */
public class JdbcTemplate {
	/**
	 * 执行对数据库的“增删改”的操作
	 * @param sql SQL语句
	 * @param params 执行预编译SQL语句时所需的参数
	 */
    public static void update(String sql, Object... params) {
    	Connection con = null;
    	PreparedStatement ps = null;
    	try {
	    	con = JdbcUtil_druid.getConn(); //贾琏
	    	ps = con.prepareStatement(sql); //欲
	    	//设置SQL语句中的参数
	    	for(int index = 0; index < params.length; index++) {
	    		ps.setObject(index+1, params[index]);
	    	}
	    	ps.executeUpdate(); //执
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally { //事
    		JdbcUtil_druid.close(con, ps, null);
    	}
    }
    /**
     * 执行查询操作，并返回查询结果
     * @param <T>  泛型机制，泛指任意类型
     * @param sql  SQL语句
     * @param handler 结果集处理器，它有两个不同的实参，一个是BeanResultSetHandler，另一个是ListResultSetHandler
     * @param params 执行预编译SQL语句时所需的参数
     * @return 返回查询结果（一个JavaBean对象或是一个List集合）
     */
    public static <T> T query(String sql, IResultSetHandler<T> handler, Object... params) {
    	Connection con = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
	    	con = JdbcUtil_druid.getConn(); //贾琏
	    	ps = con.prepareStatement(sql); //欲
	    	//设置SQL语句中的参数
	    	for(int index = 0; index < params.length; index++) {
	    		ps.setObject(index+1, params[index]);
	    	}
	    	rs = ps.executeQuery(); //执
	    	return handler.handler(rs);
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally { //事
    		JdbcUtil_druid.close(con, ps, rs);
    	}
		throw new RuntimeException("查询过程中出现错误！"); // 如果程序运行到这一步，说明一定出现了错误
    }
}
