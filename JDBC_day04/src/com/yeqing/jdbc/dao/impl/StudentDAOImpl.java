package com.yeqing.jdbc.dao.impl;

import java.util.List;

import com.yeqing.jdbc.dao.IStudentDAO;
import com.yeqing.jdbc.domain.Student;
import com.yeqing.jdbc.util.Hibernate;

// 使用自己的Hibernate模拟类
public class StudentDAOImpl implements IStudentDAO {
	public void save(Student stu) {
		Hibernate.save(stu);
	}
	public void delete(Long id) {
		Hibernate.delete(id, Student.class);
	}
	public void update(Long id, Student newStu) {
		Hibernate.update(id, newStu);
	}
	public Student get(Long id) {
		return Hibernate.get(id, Student.class);
	}
	public List<Student> listAll() {
		return Hibernate.listAll(Student.class);
	}
}