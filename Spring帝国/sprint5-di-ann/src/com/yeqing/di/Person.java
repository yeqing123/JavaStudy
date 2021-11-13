package com.yeqing.di;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

public class Person {
	@Resource
	@Qualifier("cat1")
	private Cat cat;
	@Value("${name}")
	private String name;
	@Value("${age}")
	private Integer age;
	
	@Override
	public String toString() {
		return "Person [cat=" + cat + ", name=" + name + ", age=" + age + "]";
	}

}
