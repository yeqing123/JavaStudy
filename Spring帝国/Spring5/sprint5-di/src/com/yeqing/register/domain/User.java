package com.yeqing.register.domain;


public class User {
	private Long id;
	private String username;
	private Integer age;
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public Long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public Integer getAge() {
		return age;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", age=" + age + "]";
	}
}
