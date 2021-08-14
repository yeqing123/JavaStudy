package com.yeqing.exercise.dao;

import java.util.List;

import com.yeqing.exercise.domain.Employee;

public interface IEmployeeDAO {
    /**
     * 向数据库中保存一行数据
     * @param emp 封装了一个雇员信息的Employee对象
     */
	void save(Employee emp);
	/**
	 * 删除数据库中t_employee表中指定的一行信息
	 * @param id
	 */
	void delete(Long id);
	/**
	 * 修改数据库中指定的一个雇员的信息
	 * @param id  被修改的雇员的ID
	 * @param emp 被修改的雇员的新信息
	 */
	void update(Long id, Employee emp);
	/**
	 * 查询数据库中指定的一个雇员的信息
	 * @param id 被查询的雇员的ID
	 * @return 返回一个封装了该雇员信息的Employee对象
	 */
	Employee get(Long id);
	/**
	 * 查询数据库中t_employee表中的所有雇员信息
	 * @return 返回包含了所有雇员信息的Employee对象的集合
	 */
	List<Employee> listAll();
}
