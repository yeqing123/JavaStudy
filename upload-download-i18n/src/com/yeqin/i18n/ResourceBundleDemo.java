package com.yeqin.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleDemo {
    public static void main(String[] args) {
    	ResourceBundle bundle = ResourceBundle.getBundle("app", Locale.US);
    	String username = bundle.getString("username");
    	String company = bundle.getString("company");
    	System.out.println(username + ", " + company);
	}
}
