package com.yeqing.smis.dao.impl;

import java.util.List;

import com.yeqing.smis.dao.IStudentDAO;
import com.yeqing.smis.domain.Student;
import com.yeqing.smis.handler.BeanHandler;
import com.yeqing.smis.handler.BeanListHandler;
import com.yeqing.smis.util.JdbcTemplate;



// 用JDBC实现对数据库的操作，永远记住有5个步骤：贾琏欲执事！
public class StudentDAOImpl implements IStudentDAO {
	
	public void save(Student stu) {
		String sql = "INSERT INTO t_students (name,age) VALUES (?,?)";
		JdbcTemplate.update(sql, stu.getName(), stu.getAge());
	}

	public void delete(Long id) {
		String sql = "DELETE FROM t_students WHERE id = ?";
		JdbcTemplate.update(sql, id);
	}

	public void update(Long id, Student stu) {
		String sql = "UPDATE t_students SET name=?, age=? WHERE id=?";
		JdbcTemplate.update(sql, stu.getName(), stu.getAge(), id);
	}

	public Student get(Long id) {
		String sql = "SELECT * FROM t_students WHERE id = ?";
		return JdbcTemplate.query(sql, new BeanHandler<Student>(Student.class), id);
		
	}

	public List<Student> listAll() {
		String sql = "SELECT * FROM t_students";
		return JdbcTemplate.query(sql, new BeanListHandler<Student>(Student.class));
	}

}
