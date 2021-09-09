package com.yeqin.i18n;


import java.text.MessageFormat;

import org.junit.Test;

public class MessageFormatDemo {
	public static void main(String[] args) {
		String pattern = "我是{2},你是{4},他是{1},她是{4},它是{0}";
		String str = MessageFormat.format(pattern, "A","B","C","D","E");
		//我是C,你是E,他是B,她是E,它是A
		System.out.println(str);
	}
	@Test
	public void testSql() throws Exception {
		String pattern = "SELECT * FROM {0} {1}";
		String sql = MessageFormat.format(pattern, "product", "WHERE name LIKE ?");
		//SELECT * FROM product WHERE name LIKE ?
		System.out.println(sql);
	}
}
