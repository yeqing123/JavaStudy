package com.yeqing.di_setter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Employee3 {
	private Set<String> set;
	private List<String> list;
	private String[] array;
	private Map<Integer, Cat> map1;
	private Map<String, String> map2;
	private Properties prop;
	public void setSet(Set<String> set) {
		this.set = set;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public void setArray(String[] array) {
		this.array = array;
	}
	public void setMap1(Map<Integer, Cat> map1) {
		this.map1 = map1;
	}
	public void setMap2(Map<String, String> map2) {
		this.map2 = map2;
	}
	public void setProp(Properties prop) {
		this.prop = prop;
	}
	
	public String toString() {
		return "Employee3 [\n  set=" + set + ",\n  list=" + list + ",\n  array=" + Arrays.toString(array) + ",\n  map1=" + map1
				+ "\n  map2=" + map2 + ",\n  prop=" + prop + "]";
	}
	
}
