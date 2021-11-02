package com.yeqing.mybatis.plugin;

import java.beans.Statement;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;

@Intercepts(@Signature(
		type = Executor.class, 
		method = "query", 
		args = {Statement.class,ResultHandler.class}))
public class StatementIntercepor implements Interceptor {

	public Object intercept(Invocation invocation) throws Throwable {
		
		return null;
	}

}
