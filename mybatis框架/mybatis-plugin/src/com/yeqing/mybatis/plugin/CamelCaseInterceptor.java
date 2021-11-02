package com.yeqing.mybatis.plugin;

import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

//自定义一个拦截器，将查询出来的结果中所有的带有下划线的属性名称，转换为驼峰命名规则
@Intercepts(@Signature(
		type = ResultSetHandler.class,
		method = "handleResultSets",
		args = (Statement.class)))
public class CamelCaseInterceptor implements Interceptor {
    
	private Properties properties;
	//实现拦截器的具体操作细节
	public Object intercept(Invocation invocation) throws Throwable {
        List<Object> list = (List<Object>) invocation.proceed();
        for (Object obj : list) {
			if(!(obj instanceof Map)) {
				break;
			}
			Map<String,Object> map = (Map<String, Object>) obj;
			//处理每个Map元素中的key
			handleKey(map);
		}
		return list;
	}
	//由使用拦截器的人提供相关的属性
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	private void handleKey(Map<String, Object> map) {
        Set<String> keySet = new HashSet<>(map.keySet());
        for (String key : keySet) {
		    //判断key是否为大写字母，或者是否包含下划线
        	if(key.startsWith("A") && key.endsWith("Z") || key.contains("_")) {
        		String newKey = handleKey(key);  //按照驼峰命名规则，修改key
        		map.remove(key);
        		map.put(newKey, map.get(key));
        	}
		}
	}
	private String handleKey(String key) {
		StringBuilder sb = new StringBuilder();
		boolean foundUnderline = false; //标识是否已经发现下划线
		for (int index = 0; index < key.length(); index++) {
			char ch = key.charAt(index);
			if(ch == '_') {
				foundUnderline = true;
			} else {
				if(foundUnderline) { 
					sb.append(Character.toUpperCase(ch));
				    foundUnderline = false;
				} else {
					sb.append(Character.toLowerCase(ch));
				}
			}
		}
		return sb.toString();
	}

}
