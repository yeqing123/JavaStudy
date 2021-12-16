package com.yeqing.moneylog.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.yeqing.moneylog.domain.MoneyLog;
import com.yeqing.moneylog.query.QueryObject;

public interface IMoneyLogService {

	void save(MoneyLog ml);

	void delete(Long id);

	void update(MoneyLog ml);

	MoneyLog get(Long id);

	List<MoneyLog> listAll();

	PageInfo<MoneyLog> query(QueryObject qo);
}
