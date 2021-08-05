package com.yeqing.test;


import org.junit.Test;

import com.yeqing.dao.IStudentDAO;
import com.yeqing.dao.impl.StudentDAOImpl;
import com.yeqing.domain.Student;

public class StudentDAOTest {
    private IStudentDAO dao = new StudentDAOImpl();
	@Test
	public void testSave() {
		Student stu = new Student();
		stu.setName("乔峰");
		stu.setAge(30);
		dao.save(stu);
	}

	@Test
	public void testDelete() {
		dao.delete(4L);
	}

	@Test
	public void testUpdate() {
		Student newStu = new Student();
		newStu.setName("阿飞");
		newStu.setAge(25);
		dao.update(1L, newStu);
	}

	@Test
	public void testGet() {
		System.out.println(dao.get(3L));
	}

	@Test
	public void testGetAll() {
		for (Student stu : dao.getAll()) {
			System.out.println(stu);
		}
	}

}
