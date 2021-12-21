package com.yeqing;


import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yeqing.dao.DepartmentDAO;
import com.yeqing.dao.EmployeeDAO;
import com.yeqing.pojo.Department;
import com.yeqing.pojo.Employee;

@SpringBootTest
class Springboot03WebApplicationTests {

	@Autowired
	private EmployeeDAO employeeDao;
	@Autowired
	private DepartmentDAO departmentDao;
	
	@Test
	public void testListAll() {
		Collection<Employee> list = employeeDao.listAll();
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}
    @Test
	public void testDepartmentAll() {
		Collection<Department> list = departmentDao.listAll();
		for (Department department : list) {
			System.out.println(department);
		}
	}
}
