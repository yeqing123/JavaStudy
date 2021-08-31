package com.yeqing.sims.dao;

import java.util.List;

import com.yeqing.sims.domain.Student;

//用于执行数据库操作（增删改查）
public interface IStudentDAO {
	/**
	 * 添加学生信息
	 * @param stu 新增的学生信息
	 */
    public void save(Student stu);
    /**
     * 删除执行学生的信息
     * @param id 被删除学生的ID
     */
    public void delete(Long id);
    /**
     * 修改指定学生的信息
     * @param stu 被修改学生的新的信息
     */
    public void update(Student stu);
    /**
     * 查询指定学生的信息
     * @param id 要查询的学生的ID
     * @return 返回查询到的学生信息
     */
    public Student get(Long id);
    /**
     * 查询数据库中所有学生的信息
     * @return 返回一个包含了所有学生信息的集合
     */
    public List<Student> listAll();
}
