package com.yeqing.jdbc.test;

import java.util.List;

import org.junit.Test;

import com.yeqing.jdbc.dao.IStudentDAO;
import com.yeqing.jdbc.dao.impl.StudentDAOImpl;
import com.yeqing.jdbc.domain.Student;

public class StudentDAOTest {
    private IStudentDAO studentDAO = new StudentDAOImpl();
	@Test
	public void testSave() {
		Student stu = new Student();
		stu.setName("西门吹雪");
		stu.setAge(26);
		studentDAO.save(stu);
	}

	@Test
	public void testDelete() {
		studentDAO.delete(64427L);
	}

	@Test
	public void testUpdate() {
		Student stu = new Student();
		stu.setName("司空摘星");
		stu.setAge(32);
		studentDAO.update(64428L, stu);
	}

	@Test
	public void testGet() {
		Student stu = studentDAO.get(64428L);
		System.out.println(stu);
	}

	@Test
	public void testListAll() {
		List<Student> list = studentDAO.listAll();
		for (Student student : list) {
			System.out.println(student);
		}
	}

}
