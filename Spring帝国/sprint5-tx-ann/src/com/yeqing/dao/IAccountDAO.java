package com.yeqing.dao;



public interface IAccountDAO {
    /**
     * 从指定账户中将钱转出
     * @param outId  转出账户的ID
     * @param money  转出的金额
     */
	void transferOut(Long outId, Integer money);
	/**
	 * 从指定账户中将钱转入
	 * @param inId  转入账户的ID
	 * @param money  转入的金额
	 */
	void transferIn(Long inId, Integer money);
}
