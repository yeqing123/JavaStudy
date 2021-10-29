package com.yeqing.mybatis;


import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yeqing.mybatis.domain.Student;
import com.yeqing.mybatis.domain.Teacher;
import com.yeqing.mybatis.mapper.StudentMapper;
import com.yeqing.mybatis.mapper.TeacherMapper;
import com.yeqing.mybatis.util.MybatisUtil;

public class App {
	@Test//首先测试保存学生和老师，并建立在数据库中建立他们之间的“多对多”关系
	public void testSave() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		TeacherMapper teacherMapper = session.getMapper(TeacherMapper.class);
		//先创建老师对
		Teacher t1 = new Teacher();
		Teacher t2 = new Teacher();
		t1.setName("老师1");
		t2.setName("老师2");
		//将老师信息添加到数据库中，并获取其主键值
		teacherMapper.save(t1);
		teacherMapper.save(t2);
		//创建学生对象
		Student s1 = new Student();
		Student s2 = new Student();
		s1.setName("小明");
		s2.setName("小丽");
		s1.getTeachers().add(t1);
		s1.getTeachers().add(t2);
		s2.getTeachers().add(t1);
		s2.getTeachers().add(t2);
		//将学生信息添加到数据库中，并获取其主键值
		studentMapper.save(s1);
		studentMapper.save(s2);
		//最后将每个学生对象维护的师生关系，保存在数据库中
		for(Teacher t : s1.getTeachers()) {
			studentMapper.insertRelationWithTeacher(s1.getId(), t.getId());
		}
		for(Teacher t : s2.getTeachers()) {
			studentMapper.insertRelationWithTeacher(s2.getId(), t.getId());
		}
		session.commit();
		session.close();
	}
	@Test//删除指定学生的信息以及他所维护的师生关系
	public void testDelete() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		studentMapper.delete(1L);
		studentMapper.deleteRelationWithTeacher(1L);
		session.commit();
		session.close();
	}
	@Test//查询指定学生的信息以及与他存在师生关系的老师
	public void testGet() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		Student s = studentMapper.get(2L);
		System.out.println(s);
		//因为我们使用了延迟加载功能，所以如果我们不使用学生对象中的老师集合，就不会进行额外的SQL查询
		System.out.println(s.getTeachers());
		session.close();
	}
}
