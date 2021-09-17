package com.yeqing.domain;

import lombok.Data;

@Data
public class User {
	private String name;
	private String password;
	public User() {}
	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
}
