package com.yeqing.di_setter;

public class Employee2 {
    private String username;
    private Cat c;  //Employee2类中包含一个Cat类型实例
	public void setUsername(String username) {
		this.username = username;
	}
	public void setC(Cat c) {
		this.c = c;
	}
	
	public String toString() {
		return "Employee2 [username=" + username + ", c=" + c + "]";
	}
}
