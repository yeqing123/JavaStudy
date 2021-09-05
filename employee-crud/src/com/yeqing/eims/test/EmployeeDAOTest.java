package com.yeqing.eims.test;


import java.math.BigDecimal;

import org.junit.Test;

import com.yeqing.eims.dao.IEmployeeDAO;
import com.yeqing.eims.dao.impl.EmployeeDAOImpl;
import com.yeqing.eims.domain.Employee;

public class EmployeeDAOTest {
    private IEmployeeDAO dao = new EmployeeDAOImpl();
	@Test
	public void testSave() {
		Employee emp = new Employee();
		emp.setName("张三");
		emp.setJob("办公室文员");
		emp.setSalary(new BigDecimal(3000.00));
		dao.save(emp);
	}

	@Test
	public void testDelete() {
		dao.delete(1007L);
	}

	@Test
	public void testUpdate() {
		Employee emp = new Employee();
		emp.setId(1008L);
		emp.setName("梁红");
		emp.setJob("驻村工作队队员");
		emp.setSalary(new BigDecimal(5000.00));
		dao.update(emp);
	}

	@Test
	public void testGet() {
		System.out.println(dao.get(1008L));
	}

	@Test
	public void testGetAll() {
		System.out.println(dao.getAll());
	}

}
