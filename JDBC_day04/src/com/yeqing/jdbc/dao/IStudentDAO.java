package com.yeqing.jdbc.dao;

import java.util.List;

import com.yeqing.jdbc.domain.Student;
import com.yeqing.jdbc.domain.Student2;

public interface IStudentDAO {
    /**
     * 保存学生信息
     * @param stu 新增的一个学生信息
     */
	void save(Student stu);
	/**
	 * 删除一个学生信息
	 * @param id 被删除学生的ID值
	 */
	void delete(Long id);
	/**
	 * 修改指定学生的信息
	 * @param id  被修改学生的ID值
	 * @param newStu 学生的新的信息
	 */
	void update(Long id, Student newStu);
	/**
	 * 查询指定学生的信息
	 * @param id  被查询的学生的ID值
	 * @return  返回封装学生信息的对象
	 */
	Student2 get(Long id);
	/**
	 * 查询所有学生的信息
	 * @return 返回包含所有信息对象的集合
	 */
	List<Student> listAll();
}
