package com.yeqing.dao;

import java.util.List;

import com.yeqing.domain.Employee;

//规定了对数据库“增删改查”操作规范的接口
public interface IEmployeeDAO {
	/**
	 * 向employee表中新增员工信息
	 * @param e  新增的员工信息
	 */
	void save(Employee e);
	/**
	 * 删除指定员工信息
	 * @param id  被删除员工的ID
	 */
	void delete(Long id);
	/**
	 * 修改指定员工信息
	 * @param e 保存了被修改员工的新信息
	 */
	void update(Employee e);
	/**
	 * 查询指定员工的信息
	 * @param id  被查询员工的ID
	 * @return 返回被查询员工信息
	 */
	Employee get(Long id);
	/**
	 * 查询employee表中所有员工的信息
	 * @return 返回包含了所有员工信息的List集合
	 */
	List<Employee> listAll();
}
