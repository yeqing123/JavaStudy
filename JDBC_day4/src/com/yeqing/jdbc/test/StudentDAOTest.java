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
		stu.setName("乔峰");
		stu.setAge(30);
		studentDAO.save(stu);
	}

	@Test
	public void testDelete() {
		studentDAO.delete(64426L);
	}

	@Test
	public void testUpdate() {
		Student stu = new Student();
		stu.setName("陆小凤");
		stu.setAge(28);
		studentDAO.update(64429L, stu);
	}

	@Test
	public void testGet() {
		Student stu = studentDAO.get(1L);
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
