package com.yeqing._04_junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnit4Demo {

	@Before
	public void init() {
		System.out.println("初始化1");
	}
	
	@After
	public void destroy() {
		System.out.println("删除1");
	}
	@Before
	public void init11() {
		System.out.println("初始化2");
	}
	
	@After
	public void destroy11() {
		System.out.println("删除2");
	}
	
    @Test
    public void testA() {
    	System.out.println("A测试完成");
    }
    @Test
    public void testB() {
    	System.out.println("B测试完成");
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

