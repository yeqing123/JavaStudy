package com.yeqing.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yeqing.dao.IStudentDAO;
import com.yeqing.domain.Student;
import com.yeqing.util.JdbcUtil;

public class StudentDAOImpl implements IStudentDAO {

	// 口诀：贾琏欲执事
	public void save(Student stu) {
		// 拼接SQL语句
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO t_students (name,age) VALUES ('").append(stu.getName()).append("',").append(stu.getAge())
				.append(")");

		// 1，加载注册驱动，并获得连接对象
		Connection conn = JdbcUtil.getConnection();
		Statement st = null;
		try {
			// 2,创建SQL语句
			st = conn.createStatement();
			// 3,执行SQL语句
			st.executeUpdate(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 4，释放资源
			JdbcUtil.close(conn, st, null);
		}
	}

	public void delete(Long id) {
		// 拼接SQL语句
		String sql = "DELETE FROM t_students WHERE id=" + id;
		// 1，加载注册驱动，并获得连接对象
		Connection conn = JdbcUtil.getConnection();
		Statement st = null;
		try {
			// 2,创建SQL语句
			st = conn.createStatement();
			// 3,执行SQL语句
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 4，释放资源
			JdbcUtil.close(conn, st, null);
		}
	}

	public void update(Long id, Student newStu) {
		// 拼接SQL语句
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE t_students SET name='").append(newStu.getName()).append("',").append("age=")
				.append(newStu.getAge()).append(" WHERE id=").append(id);

		// 1，加载注册驱动，并获得连接对象
		Connection conn = JdbcUtil.getConnection();
		// 2,创建SQL语句
		Statement st = null;
		try {
			st = conn.createStatement();
			// 3,执行SQL语句
			st.executeUpdate(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 4，释放资源
			JdbcUtil.close(conn, st, null);
		}

	}

	public Student get(Long id) {
		// 拼接SQL语句
		String sql = "SELECT * FROM t_students WHERE id=" + id;
		// 1，加载注册驱动，并获得连接对象
		Connection conn = JdbcUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			// 2,创建SQL语句
			st = conn.createStatement();
			// 3,执行SQL语句
			rs = st.executeQuery(sql);
			// 处理结果集
			if (rs.next()) {
				Student stu = new Student();
				stu.setId(rs.getLong("id"));
				stu.setName(rs.getString("name"));
				stu.setAge(rs.getInt("age"));
				return stu;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 4，释放资源
			JdbcUtil.close(conn, st, rs);
		}
		return null;
	}

	public List<Student> getAll() {
		// 拼接SQL语句
		String sql = "SELECT * FROM t_students";
		// 1，加载注册驱动，并获得连接对象
		Connection conn = JdbcUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		List<Student> list = new ArrayList();
		try {
			// 2,创建SQL语句
			st = conn.createStatement();
			// 3,执行SQL语句
			rs = st.executeQuery(sql);
			// 处理结果集
			while (rs.next()) {
				Student stu = new Student();
				stu.setId(rs.getLong("id"));
				stu.setName(rs.getString("name"));
				stu.setAge(rs.getInt("age"));
				list.add(stu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 4，释放资源
			JdbcUtil.close(conn, st, rs);
		}
		return list;
	}

}
