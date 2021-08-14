package com.yeqing.exercise.test;


import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import com.yeqing.exercise.dao.IEmployeeDAO;
import com.yeqing.exercise.dao.impl.EmployeeDAOImpl;
import com.yeqing.exercise.domain.Employee;

public class EmployeeDAOTest {
    private IEmployeeDAO dao = new EmployeeDAOImpl();	
	@Test
	public void testSave() {
		Employee emp = new Employee();
		emp.setName("沙玉燕");
		emp.setJob("司法局法制办工作人员");
		emp.setSalary(new BigDecimal(5000.00));
		dao.save(emp);
	}

	@Test
	public void testDelete() {
		dao.delete(1016L);
	}

	@Test
	public void testUpdate() {
		Employee emp = new Employee();
		emp.setName("周瑞超");
		emp.setJob("司法局基层科负责人");
		emp.setSalary(new BigDecimal(5500.55));
		dao.update(1010L, emp);
	}

	@Test
	public void testGet() {
		Employee emp = dao.get(1014L);
		System.out.println(emp);
	}

	@Test
	public void testListAll() {
		List<Employee> list = dao.listAll();
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}

}
