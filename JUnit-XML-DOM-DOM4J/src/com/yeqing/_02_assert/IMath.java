package com.yeqing._02_assert;

/**
 * 计算两个数的和与商
 * @author yeqin
 * 
 */
public interface IMath {
    /**
     * 计算两个数的和
     * @param a是一个整数
     * @param b是另一个整数
     * @return 返回两个数的和
     */
	public int add(int a, int b);
    
	/**
	 * 计算两个数的商
	 * @param a是被除数
	 * @param b是除数
	 * @return 返回它们的商
	 */
	public int divide(int a, int b);
}
