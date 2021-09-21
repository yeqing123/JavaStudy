
package com.yeqing.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class RequestAttributeListener implements ServletRequestAttributeListener {

	public void attributeAdded(ServletRequestAttributeEvent srae) {
	//	System.out.println("在request作用域中添加属性：" + srae.getName());
	}

	public void attributeRemoved(ServletRequestAttributeEvent srae) {
	//	System.out.println("在request作用域中删除属性：" + srae.getName());
		
	}

	public void attributeReplaced(ServletRequestAttributeEvent srae) {
	//	System.out.println("在request作用域中替换属性：" + srae.getName());
		
	}

}
