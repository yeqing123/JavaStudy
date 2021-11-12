package com.yeqing.bean_inheritance;


public class TagBean2 {
	private String name;
	private Integer age;
	private String weight;
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "TagBean2 [name=" + name + ", age=" + age + ", weight=" + weight + "]";
	}
}
