package com.yeqing.proxt;

import com.yeqing.domain.Employee;
import com.yeqing.service.IEmployeeService;
import com.yeqing.tx.TransactionManager;

//定义一个静态代理类，用于增强IEmployeeService接口实现类的功能
public class EmployeeServiceProxy implements IEmployeeService {

	private IEmployeeService target;
	private TransactionManager txManager;

	public void setTarget(IEmployeeService target) {
		this.target = target;
	}

	public void setTxManager(TransactionManager txManager) {
		this.txManager = txManager;
	}

	public void save(Employee emp) {
		txManager.begin();   //开启事务
		try {
			target.save(emp);
			txManager.commit(); //提交事务
		} catch(Exception e) {
			e.printStackTrace();
			txManager.rollback();  //回滚事务
		}
	}

	public void update(Employee emp) {
		txManager.begin();   //开启事务
		try {
			target.update(emp);
			txManager.commit(); //提交事务
		} catch(Exception e) {
			e.printStackTrace();
			txManager.rollback();  //回滚事务
		}
	}
}
