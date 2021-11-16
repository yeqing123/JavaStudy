package com.yeqing.log;

import java.util.Date;

public class LogUtil {
	
	public void writeLog(String className, String methodName) {
		System.out.println(new Date().toLocaleString() +  "，调用了" + className + "类中的" + methodName + "方法");
	}

}
