package com.yeqing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeqing.dao.IAccountDAO;
import com.yeqing.service.IAccountService;

@Service
@Transactional
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private IAccountDAO dao;

	public void transfer(Long outId, Long inId, Integer money) {
		dao.transferOut(outId, money);
		int i = 1 / 0;  //模拟程序异常
		dao.transferIn(inId, money);
	}

}
