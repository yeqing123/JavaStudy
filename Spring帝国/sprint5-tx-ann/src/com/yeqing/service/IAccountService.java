package com.yeqing.service;


public interface IAccountService {
    /**
     * 业务方法，表示银行转账服务
     * @param outId  转出账户
     * @param inId   转入账户
     * @param money  转账金额
     */
	void transfer(Long outId, Long inId, Integer money);
}
