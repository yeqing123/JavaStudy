package com.yeqing._01_junit.junit4;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class EmployeeDAOTest {
	
	@Test
    public void testSave() throws Exception {
		System.out.println("员工保存");
	}
    
	@Test
    public void testDelete() throws Exception {
		System.out.println("员工删除");
	}
    
	@BeforeClass
	public static void staticInit() throws Exception {
		System.out.println("staticInit");
	}
	
	@AfterClass
	public static void staticDestroy() throws Exception {
		System.out.println("staticDestroy");
	}
    
}
