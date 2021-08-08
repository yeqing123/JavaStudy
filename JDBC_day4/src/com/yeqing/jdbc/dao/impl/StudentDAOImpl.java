package com.yeqing.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yeqing.jdbc.dao.IStudentDAO;
import com.yeqing.jdbc.domain.Student;
import com.yeqing.jdbc.util.JdbcTemplate;
import com.yeqing.jdbc.util.JdbcUtil;

// 永远记住，操作数据库的5个步骤：贾琏欲执事
public class StudentDAOImpl implements IStudentDAO {

	public void save(Student stu) {
		String sql = "INSERT INTO t_students (name,age) VALUES (?,?)";
		JdbcTemplate.update(sql, stu.getName(), stu.getAge());
	}

	public void delete(Long id) {
		String sql = "DELETE FROM t_students WHERE id = ?";
		JdbcTemplate.update(sql, id);
	}

	public void update(Long id, Student newStu) {
		String sql = "UPDATE t_students SET name = ?, age = ? WHERE id = ?";
		JdbcTemplate.update(sql, newStu.getName(), newStu.getAge(), id);
	}

	public Student get(Long id) {
		String sql = "SELECT * FROM t_students WHERE id = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection(); //贾琏
			ps = conn.prepareStatement(sql); //欲
			//设置预编译语句中的值
			ps.setLong(1, id);
			rs = ps.executeQuery(); //执，无参数
			//处理结果集，将查询出的一行信息封装成一个对象
			if(rs.next()) {
				Student stu = new Student();
				stu.setId(rs.getLong("id"));
				stu.setName(rs.getString("name"));
				stu.setAge(rs.getInt("age"));
				return stu;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, ps, rs);//事
		}
		return null;
	}

	public List<Student> listAll() {
		String sql = "SELECT * FROM t_students";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Student> list = new ArrayList();
		try {
			conn = JdbcUtil.getConnection(); //贾琏
			ps = conn.prepareStatement(sql); //欲
			rs = ps.executeQuery(); //执，无参数
			//处理结果集，将查询出的每一行信息封装成一个个对象，放入集合中
			while(rs.next()) {
				Student stu = new Student();
				stu.setId(rs.getLong("id"));
				stu.setName(rs.getString("name"));
				stu.setAge(rs.getInt("age"));
				list.add(stu);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, ps, rs);//事
		}
		return list;
	}

}
