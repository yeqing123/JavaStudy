package com.yeqing._02_el;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
	private Long id = 123L;
	private String name = "Jack";
	private String[] hobbys = { "java", "c", "girl" };
	private String company = "小码哥教育";
	private List<String> list = Arrays.asList("AA","BB","CC","DD");
	private Map<String, Object> map = new HashMap<String, Object>() {
		{
			this.put("s_id", 333L);
	        this.put("s_name", "东方姑娘");
	        this.put("www.xiaomage.com", "假域名");
	        this.put("s_age", 18);
	    }
	};
}
