package com.yeqing.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yeqing.pojo.Employee;

@Repository
public class EmployeeDAO {
    //模拟数据库操作
	private static Map<String, Employee> employees;
    static {
    	employees = new HashMap<>();
    	employees.put("1001",new Employee(1001L, "AA", "AA51295367@qq.com", 0, 001L)); 
    	employees.put("1002",new Employee(1002L, "BB", "BB51295367@qq.com", 1, 002L)); 
    	employees.put("1003",new Employee(1003L, "CC", "CC51295367@qq.com", 0, 003L)); 
    	employees.put("1004",new Employee(1004L, "DD", "DD51295367@qq.com", 1, 004L)); 
    
    }
    //模拟主键自增操作
    private Long initId = 1005L;
    //增加员工信息
    public void save(Employee e) {
    	initId++;
    	e.setId(initId);
    	employees.put(String.valueOf(initId), e);
    }
    //删除指定员工信息
    public void delete(Long id) {
    	employees.remove(String.valueOf(id));
    }
    //查询指定员工信息
    public Employee getEmployeeById(Long id) {
    	return employees.get(String.valueOf(id));
    }
    //查询所有员工信息
    public Collection<Employee> listAll() {
    	return employees.values();
    }
}
