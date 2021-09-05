package com.yeqing.eims.dao;

import java.util.List;

import com.yeqing.eims.domain.Employee;

public interface IEmployeeDAO {
    /**
     * 添加雇员信息
     * @param emp 添加的新雇员信息
     */
	void save(Employee emp);
	/**
	 * 删除指定雇员的信息
	 * @param id 被删除的指定雇员ID
	 */
	void delete(Long id);
	/**
	 * 修改指定雇员信息
	 * @param emp 要修改的雇员的新信息
	 */
	void update(Employee emp);
	/**
	 * 查询指定雇员的信息
	 * @param id 要查新的雇员的ID
	 * @return 返回封装了雇员信息的对象
	 */
	Employee get(Long id);
	/**
	 * 查询所有雇员的信息
	 * @return 返回包含了所有雇员对象的一个List集合
	 */
	List<Employee> getAll();
}
