package com.yeqing.tx;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TransactionManager {
	
	@Pointcut("execution(* com.yeqing.service.*Service.*(..))")
	private void txPoint() {
	}
	
	@Before("txPoint()")
	public void begin(JoinPoint jp) {
		System.out.println("开启事务");
	}
	@AfterReturning("txPoint()")
	public void commit(JoinPoint jp) {
		System.out.println("提交事务");
	}
	@AfterThrowing(pointcut = "txPoint()", throwing = "ex")
	public void rollback(JoinPoint jp, Throwable ex) {
		System.out.println("回滚事务，出错信息：" + ex.getMessage());
	}
	@After("txPoint()")
	public void close(JoinPoint jp) {
		System.out.println("释放资源");
	}

	//定义一个环绕增强的方法，注意ProceedingJoinPoint参数只能用在环绕增强中
	//@Around("txPoint()")
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
