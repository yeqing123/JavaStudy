package com.yeqin.i18n;


import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

public class LocaleTest {
	
	@Test
	public void testDateFormat() throws Exception {
		System.out.println(new Date());
		DateFormat format = DateFormat.getDateInstance();
		System.out.println(format.format(new Date()));
	}
	@Test
	public void testNumberFormat() throws Exception {
		double money = 123456789.78;
		NumberFormat format = NumberFormat.getCurrencyInstance();
		System.out.println(format.format(money));
		System.out.println(NumberFormat.getCurrencyInstance(Locale.UK).format(money));
		System.out.println(NumberFormat.getCurrencyInstance(Locale.FRANCE).format(money));
		System.out.println(NumberFormat.getCurrencyInstance(Locale.US).format(money));
	}
}
