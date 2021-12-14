package com.yeqing.moneylog.mapper;

import java.util.List;

import com.yeqing.moneylog.domain.MoneyLog;
import com.yeqing.moneylog.query.QueryObject;

//操作money_log表的映射文件
public interface MoneyLogMapper {
	int insert(MoneyLog ml);

	void deleteById(Long id);

	void updateById(MoneyLog ml);

	MoneyLog selectById(Long id);

	List<MoneyLog> selectAll();

	List<MoneyLog> query(QueryObject qo);
}
