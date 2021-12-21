package com.yeqing.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yeqing.pojo.Department;

@Repository
public class DepartmentDAO {
	//使用一个Map集合在模拟数据库
	private static Map<String, Department> departments;
	//处理主键自增
	private Long initId = 005L;
	
	static {
		departments = new HashMap<>();
		departments.put("001", new Department(001L, "技术部"));
		departments.put("002", new Department(002L, "教育部"));
		departments.put("003", new Department(003L, "人事部"));
		departments.put("004", new Department(004L, "财务部"));
	}
	//保存新部门
	public void save(Department dept) {
		initId++;
		dept.setId(initId);
		departments.put(String.valueOf(initId), dept);
	}
	//查询指定的部门
	public Department getDepartmentById(Long id) {
		return departments.get(String.valueOf(id));
	}
	//查询所有的部门信息
	public Collection<Department> listAll() {
		return departments.values();
	}
	//删除指定部门信息
	public void deleteById(Long id) {
		departments.remove(String.valueOf(id));
	}
}
