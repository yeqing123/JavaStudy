package com.yeqing._02_assert;

import org.junit.Assert;
import org.junit.Test;

public class MathTest {
	
    MathImpl math = new MathImpl();
    
	@Test
	public void testAdd() {
		int ret = math.add(1, 2);
		
		Assert.assertEquals(3, ret);
	}

	@Test
	public void testDivide() {
		int ret = math.divide(12, 3);
		
		Assert.assertEquals(4, ret);
	}

	@Test(expected=ArithmeticException.class, timeout=10)
	public void testDivide2() {
		int ret = math.divide(12, 0);
		
		Assert.assertEquals(4, ret);
	}
}
