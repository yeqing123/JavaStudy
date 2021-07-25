package com.yeqing._01_junit.junit3;

import junit.framework.TestCase;

public class EmployeeDAOTest extends TestCase {
	
    public void testSave() throws Exception {
		System.out.println("员工保存");
	}
    
    public void testDelete() throws Exception {
		System.out.println("员工删除");
	}
    
    @Override
    protected void setUp() throws Exception {
        System.out.println("初始化");
    }
    
    @Override
    protected void tearDown() throws Exception {
    	System.out.println("销毁");
    }
}
