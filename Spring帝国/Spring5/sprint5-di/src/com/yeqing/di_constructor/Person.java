package com.yeqing.di_constructor;

import java.util.Properties;

import com.yeqing.di_setter.Cat;

public class Person {
	private String username;
	private Cat cat;
	private Integer age;
	private Properties prop;
	
	public Person(String username, Cat cat, Integer age, Properties prop) {
		super();
		this.username = username;
		this.cat = cat;
		this.age = age;
		this.prop = prop;
	}

	public String toString() {
		return "Person [username=" + username + ", cat=" + cat + ", age=" + age + ", prop=" + prop + "]";
	}
	
	
	
}
