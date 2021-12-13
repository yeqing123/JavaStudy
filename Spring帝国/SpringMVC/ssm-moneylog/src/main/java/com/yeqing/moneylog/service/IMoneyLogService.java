package com.yeqing.moneylog.service;

import java.util.List;

import com.yeqing.moneylog.domain.MoneyLog;

public interface IMoneyLogService {

	void save(MoneyLog ml);

	void delete(Long id);

	void update(MoneyLog ml);

	MoneyLog get(Long id);

	List<MoneyLog> listAll();
}
