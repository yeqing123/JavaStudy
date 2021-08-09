package com.yeqing.jdbc.dao.impl;

import java.util.List;
import com.yeqing.jdbc.dao.IStudentDAO;
import com.yeqing.jdbc.domain.Student;
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
	public Student get(Long id) {
		List<Student> list = JdbcTemplate.query("SELECT * FROM t_students WHERE id = ?", 
				Student.class, id);
		return list.size() == 1 ? list.get(0) : null;
	}
	public List<Student> listAll() {
		return JdbcTemplate.query("SELECT * FROM t_students", Student.class);
	}
}
