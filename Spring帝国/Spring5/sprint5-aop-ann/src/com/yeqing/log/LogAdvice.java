package com.yeqing.log;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAdvice {
	
	@Pointcut("execution(* com.yeqing.service.*Service.*(..))")
	private void logPoint() {}
	
	@Before("logPoint()")
	public void writeLog(JoinPoint jp) {
		System.out.println("调用日志：" + new Date().toLocaleString() + "  调用了" + jp.getSignature().getName() + "方法");
	}
}
