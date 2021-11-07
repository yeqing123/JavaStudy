package com.yeqing.hello;

public class HelloWorld {
	private String username;
	private Integer age;
	
	public void setUsername(String username) {
		this.username = username;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public void sayHello() {
		System.out.println("你好" + username + "，年龄：" + age + ", 欢迎来到Spring帝国");
	}
}
