package com.yeqing.sims.dao.impl;

import java.util.List;

import com.yeqing.sims.dao.IStudentDAO;
import com.yeqing.sims.domain.Student;
import com.yeqing.sims.util.MyHibernate;

//实现数据库操作（增删改查）的方法
//永远记住5个操作步骤：贾琏欲执事
public class StudentDAOImpl implements IStudentDAO {
	MyHibernate hibernate = new MyHibernate("t_students");
	public void save(Student stu) {
		hibernate.save(stu);
	}

	public void delete(Long id) {
		hibernate.delete(id, Student.class);
	}

	public void update(Student stu) {
		hibernate.update(stu);
	}

	public Student get(Long id) {
		return hibernate.get(id, Student.class);
	}

	public List<Student> listAll() {
		return hibernate.getAll(Student.class);
	}

}
