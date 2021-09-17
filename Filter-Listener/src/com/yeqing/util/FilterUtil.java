package com.yeqing.util;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

//对敏感字进行过滤的工具类
public class FilterUtil {
	private static List<String> sensitiveWords = new ArrayList<>();
	static {
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("sensitive_word.txt");
		Scanner scanner = new Scanner(input);
		while(scanner.hasNext()) {
			String word = scanner.nextLine();
			sensitiveWords.add(word);
		}
		scanner.close();
	}
	/**
	 * 过滤字符串中的敏感字，将它们替换成*
	 * @param text 被过滤的字符串
	 * @return 返回过滤后的字符串
	 */
	public static String filter(String text) {
		StringBuilder str = new StringBuilder(text);
		for(String word : sensitiveWords) {  //遍历敏感字集合
			int index = -1;    
			while((index = str.indexOf(word, index+1)) != -1) { //判断被过滤的语句中是否包含敏感字，并获取敏感字首次出现的索引值
				str.replace(index, index+word.length(), getReplacement(word)); //将敏感字替换成*字符串
			}
		}
		return str.toString();
	}
	//将敏感字替换成等长的*字符串
	private static String getReplacement(String word) {
		StringBuilder replacement = new StringBuilder(); //保存替换字符串
		for(int i = 0; i < word.length(); i++) { //根据敏感字的长度，得到相等长度的*
			replacement.append("*");
		}
		return replacement.toString();
	}
	@Test
	public void testFilter() throws Exception {
		String str = FilterUtil.filter("你是一个傻逼，你不可能打老子撒。你个婊子傻逼傻逼老子，婊子，哈哈，傻逼老子，婊子子婊");
		System.out.println(str);
	}
}
