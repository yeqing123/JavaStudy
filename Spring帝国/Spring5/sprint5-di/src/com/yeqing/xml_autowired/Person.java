package com.yeqing.xml_autowired;

import java.math.BigDecimal;

public class Person {
	private Dog dog;
	private String username;

	public void setUsername(String username) {
		this.username = username;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}

	@Override
	public String toString() {
		return "Person [dog=" + dog + ", username=" + username + "]";
	}

	
}
