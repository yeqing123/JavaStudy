package com.yeqing.bean_inheritance;


public class TagBean1 {
	private String name;
	private Integer age;
	private String color;
	
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public String toString() {
		return "TagBean1 [name=" + name + ", age=" + age + ", color=" + color + "]";
	}
}
