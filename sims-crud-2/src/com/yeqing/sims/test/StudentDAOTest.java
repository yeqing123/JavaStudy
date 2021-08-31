package com.yeqing.sims.test;


import java.util.List;

import org.junit.Test;

import com.yeqing.sims.dao.IStudentDAO;
import com.yeqing.sims.dao.impl.StudentDAOImpl;
import com.yeqing.sims.domain.Student;

public class StudentDAOTest {
    private IStudentDAO dao = new StudentDAOImpl();
	@Test
	public void testSave() {
		Student stu = new Student("叶青", 30);
		dao.save(stu);
	}

	@Test
	public void testDelete() {
		dao.delete(92454L);
	}

	@Test
	public void testUpdate() {
		Student stu = new Student("刘莎", 20);
		stu.setId(92457L);
		dao.update(stu);
	}

	@Test
	public void testGet() {
		Student s = dao.get(92445L);
		System.out.println(s);
	}

	@Test
	public void testListAll() {
		List<Student> list = dao.listAll();
		System.out.println(list);
	}

}
