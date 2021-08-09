package com.yeqing.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


// 数据库操作的模版工具类
public class JdbcTemplate {
    
	//用于“增删改”操作的模版
    public static void update(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection(); //贾琏
			ps = conn.prepareStatement(sql); //欲
			for(int index = 0; index < params.length; index++) {
				ps.setObject(index + 1, params[index]);
			}
			ps.executeUpdate();//执
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, ps, null);//事
		}
    }
    // 用于查询操作的模板
    public static <T> List<T> query(String sql, Class<T> classType, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<T> list = new ArrayList();
		try {
			conn = JdbcUtil.getConnection(); //贾琏
			ps = conn.prepareStatement(sql); //欲
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			rs = ps.executeQuery(); //执，无参数
            // 获取数据对象的所有已声明的字段类的数组
			Field[] fs = classType.getDeclaredFields();
			//处理结果集
			while(rs.next()) {
				T obj = classType.newInstance();  // 实例化数据对象
				// 向数据对象中设置每个属性值
				for (int index = 0; index < fs.length; index++) {
					String fieldName = fs[index].getName();   // 获取属性名
					String headChar = fieldName.substring(0, 1).toUpperCase(); // 获取属性名的首字母并变成大写
					String setterName = "set" + headChar + fieldName.substring(1); // 拼接setter方法名
					Method m = classType.getMethod(setterName, fs[index].getType()); // 获取每个setXxx方法
					m.invoke(obj, rs.getObject(fieldName)); // 调用setXxx方法，给对象设置属性值
				}
				list.add(obj);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, ps, rs);//事
		}
		return list;
	}
}
