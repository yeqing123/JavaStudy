package com.yeqing.mybatis.plugin;

import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

@Intercepts(@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }))
public class CamelCaseInterceptor implements Interceptor {

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
	}

	public Object intercept(Invocation invocation) throws Throwable {
		List<Object> list = (List<Object>) invocation.proceed();
        
		for (Object obj : list) {
			if(!(obj instanceof Map)) {
				break;
			}
			handlerKey((Map<String,Object>) obj);
		}
		return list;
	}

	private void handlerKey(Map<String, Object> map) {
		Set<String> keys = map.keySet();
		for (String key : keys) {
			if (key.startsWith("A") && key.endsWith("Z") || key.contains("_")) {
				String newKey = handlerString(key);
				Object val = map.get(key);
				map.remove(key);
				map.put(newKey, val);
			}
		}
	}

	private String handlerString(String str) {
		boolean findUnderline = false; // 表示是否找到了下划线
		StringBuilder sb = new StringBuilder();
		for (int index = 0; index < str.length(); index++) {
			char ch = str.charAt(index);
			if (ch == '_') {
				findUnderline = true;
			} else {
				if (!findUnderline) {
					sb.append(Character.toLowerCase(ch)); // 如果没找到下划线，拼入小写字母
				} else {
					sb.append(Character.toUpperCase(ch)); // 如果已经找到下划线了，就拼入大写字母
					findUnderline = false;
				}
			}
		}
		return sb.toString();
	}
}
