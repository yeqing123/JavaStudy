package com.yeqing.lifecycle;

public class MyDataSource {
	
	public MyDataSource() {
		System.out.println("构建MyDataSource对象...");
	}
	public void open() {
		System.out.println("打开资源");
	}
	public void dowork() {
		System.out.println("工作中......");
	}
	public void close() {
		System.out.println("关闭资源");
	}
}
