package com.yeqing.tx;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class TransactionManager {
	
	public void begin(JoinPoint jp) {
		System.out.println("当前连接点类型：" + jp.getKind());
		System.out.println("代理对象：" + jp.getThis().getClass());
		System.out.println("目标对象：" + jp.getTarget().getClass());
		System.out.println("被增强的方法参数：" + Arrays.toString(jp.getArgs()));
		System.out.println("连接点签名：" + jp.getSignature());
		System.out.println("开启事务");
	}
	public void commit(JoinPoint jp) {
		System.out.println("提交事务");
	}
	public void rollback(JoinPoint jp, Throwable ex) {
		System.out.println("回滚事务，" + ex.getMessage());
	}
	public void close(JoinPoint jp) {
		System.out.println("释放资源");
	}

	//定义一个环绕增强的方法，注意ProceedingJoinPoint参数只能用在环绕增强中
	public Object around(ProceedingJoinPoint pjp) {
		Object ret = null;
		this.begin(pjp);
		try {
			ret = pjp.proceed();
			this.commit(pjp);
		} catch(Throwable e) {
			e.printStackTrace();
			this.rollback(pjp, e);
		}finally {
			this.close(pjp);
		}
		return ret;
	}
}
