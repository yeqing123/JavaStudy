package com.yeqing._03_anno;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


public class EmployeeDemo {
	public static void main(String[] args) throws Exception {
		Annotation[] ans = Employee.class.getAnnotations();
		for (Annotation a : ans) {
			System.out.println(a);
		}
		
		Method[] mds = Employee.class.getMethods();
		
		for (Method method : mds) {
			Annotation an = method.getAnnotation(Deprecated.class);
			if(an != null) {
				System.out.println(an);
			    System.out.println(method);
			}
		}
	}
	
}
