package com.yeqing.ioc.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class MyDataSource {
	@PostConstruct
	public void open() {
		System.out.println("打开资源");
	}
	@PreDestroy
	public void close() {
		System.out.println("关闭资源前的扫尾工作");
	}
	public void doWork() {
		System.out.println("工作......");
	}
	public MyDataSource() {
		System.out.println("构建MyDataSource对象");
	}
}
