package com.yeqing.dao;

import java.util.List;

import com.yeqing.domain.Student;

public interface IStudentDAO {
    /**
     * 向表中的添加一行学生信息
     * @param  要保存的学生对象
     */
	void save(Student stu);
	/**
	 * 删除指定的学生信息
	 * @param id  被删除学生的ID值
	 */
	void delete(Long id);
	/**
	 * 修改指定学生的信息
	 * @param id  被修改的学生的ID值
	 * @param newStu  学生的新信息
	 */
	void update(Long id, Student newStu);
	/**
	 * 获取指定学生的信息
	 * @param id  学生的ID值
	 * @return  返回被查询学生的信息
	 */
	Student get(Long id);
	/**
	 * 获取所有的学生信息
	 * @return 返回包含所有学生信息的集合
	 */
	List<Student> getAll();
}
