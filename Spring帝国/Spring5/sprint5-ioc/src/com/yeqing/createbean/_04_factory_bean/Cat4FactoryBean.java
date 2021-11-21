package com.yeqing.createbean._04_factory_bean;

import org.springframework.beans.factory.FactoryBean;

//实现了FactoryBean接口的工厂类中具有相同的实例化方法名getObejct()
public class Cat4FactoryBean implements FactoryBean<Cat4> {

	private String username;
	//还可以给Cat4FactoryBean类注入属性操作
	public void setUsername(String username) {
		this.username = username;
	}
	public Cat4 getObject() throws Exception {
		System.out.println(username);
		return new Cat4();
	}

	public Class<?> getObjectType() {
		return Cat4.class;
	}
	//覆写FactoryBean接口中的方法，取消单例机制
    public boolean isSingleton() {
		return false;
	}

}
