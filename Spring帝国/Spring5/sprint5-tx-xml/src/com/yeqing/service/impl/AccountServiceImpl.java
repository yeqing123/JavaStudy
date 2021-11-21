package com.yeqing.service.impl;

import com.yeqing.dao.IAccountDAO;
import com.yeqing.service.IAccountService;

public class AccountServiceImpl implements IAccountService {

	private IAccountDAO dao;
	
	public void setDao(IAccountDAO dao) {
		this.dao = dao;
	}
	
	public void transfer(Long outId, Long inId, Integer money) {
		dao.transferOut(outId, money);
		int i = 1 / 0;  //模拟程序异常
		dao.transferIn(inId, money);
	}

}
