package com.yeqing._04_junit;


public class MyJUnitDemo {

	@MyBefore
	public void init() {
		System.out.println("初始化1");
	}
	
	@MyAfter
	public void destroy() {
		System.out.println("删除1");
	}
	@MyBefore
	public void init11() {
		System.out.println("初始化2");
	}
	
	@MyAfter
	public void destroy11() {
		System.out.println("删除2");
	}
	
    @MyTest
    public void testA() {
    	System.out.println("A测试完成");
    }
    @MyTest
    public void testB() {
    	System.out.println("B测试完成");
    }
    
    public static void main(String[] args) throws Exception {
        MyJUnit.myJunit(MyJUnitDemo.class);
    }
}

