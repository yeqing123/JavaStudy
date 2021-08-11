package com.yeqing.jdbc.dao.impl;

import java.util.List;

import com.yeqing.jdbc.dao.IStudentDAO;
import com.yeqing.jdbc.domain.Student;
import com.yeqing.jdbc.domain.Student2;
import com.yeqing.jdbc.handler.BeanHandler;
import com.yeqing.jdbc.handler.BeanListHandler;
import com.yeqing.jdbc.handler.IResultSetHandler;
import com.yeqing.jdbc.util.JdbcTemplate;

// 永远记住，操作数据库的5个步骤：贾琏欲执事
public class StudentDAOImpl implements IStudentDAO {
	public void save(Student stu) {
		JdbcTemplate.update("INSERT INTO t_students (name,age) VALUES (?,?)", 
				stu.getName(), stu.getAge());
	}
	public void delete(Long id) {
		JdbcTemplate.update("DELETE FROM t_students WHERE id = ?", id);
	}
	public void update(Long id, Student newStu) {
		JdbcTemplate.update("UPDATE t_students SET name = ?, age = ? WHERE id = ?", 
				newStu.getName(), newStu.getAge(), id);
	}
	public Student2 get(Long id) {
		IResultSetHandler<Student2> handler = new BeanHandler<>(Student2.class);
		return JdbcTemplate.query("SELECT * FROM t_students WHERE id = ?", 
				handler, id);
	}
	public List<Student> listAll() {
		IResultSetHandler<List<Student>> handler = new BeanListHandler<>(Student.class);
		return JdbcTemplate.query("SELECT * FROM t_students", handler);
	}
}