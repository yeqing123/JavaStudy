package com.yeqing.container;

public class Person {
	//使用Spring Ioc容器时必须有一个无参构造器
    public Person() {
    	System.out.println("构建Person对象...");
    }
}
