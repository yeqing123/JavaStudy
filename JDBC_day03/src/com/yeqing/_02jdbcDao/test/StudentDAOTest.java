package com.yeqing._02jdbcDao.test;

import java.util.List;

import org.junit.Test;

import com.yeqing._02jdbcDao.dao.IStudentDAO;
import com.yeqing._02jdbcDao.dao.impl.StudentDAOImpl;
import com.yeqing._02jdbcDao.domain.Student;

public class StudentDAOTest {
    private IStudentDAO studentDAO = new StudentDAOImpl();
	@Test
	public void testSave() {
		Student stu = new Student();
		stu.setName("ABC");
		stu.setAge(18);
		studentDAO.save(stu);
	}

	@Test
	public void testDelete() {
		studentDAO.delete(8L);
	}

	@Test
	public void testUpdate() {
		Student stu = new Student();
		stu.setName("XYZ");
		stu.setAge(30);
		studentDAO.update(6L, stu);
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
