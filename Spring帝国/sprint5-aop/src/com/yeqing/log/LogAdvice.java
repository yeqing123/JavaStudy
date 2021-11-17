package com.yeqing.log;

import java.util.Date;

import org.aspectj.lang.JoinPoint;

public class LogAdvice {
	public void writeLog(JoinPoint jp) {
		System.out.println(new Date().toLocaleString() + ", 调用了" + jp.getSignature().getName() + "方法");
	}
}
