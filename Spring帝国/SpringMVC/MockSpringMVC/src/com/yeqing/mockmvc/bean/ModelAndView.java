package com.yeqing.mockmvc.bean;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
	private String viewName;
	private Map<String, Object> model = new HashMap<>();

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void addModel(String string, Object value) {
		model.put(string, value);
	}
}
