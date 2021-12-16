package com.yeqing.moneylog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.yeqing.moneylog.domain.MoneyLog;
import com.yeqing.moneylog.mapper.MoneyLogMapper;
import com.yeqing.moneylog.query.QueryObject;
import com.yeqing.moneylog.service.IMoneyLogService;

@Service
public class MoneyLogServiceImpl implements IMoneyLogService {

	@Autowired(required = false)
	private MoneyLogMapper mapper;
	
	public void save(MoneyLog ml) {
		mapper.insert(ml);
	}

	public void delete(Long id) {
		mapper.deleteById(id);
	}

	public void update(MoneyLog ml) {
		mapper.updateById(ml);
	}

	public MoneyLog get(Long id) {
		return mapper.selectById(id);
	}

	public List<MoneyLog> listAll() {
		return mapper.selectAll();
	}
	public PageInfo<MoneyLog> query(QueryObject qo) {
		List<MoneyLog> list = mapper.query(qo);
		return new PageInfo<>(list);
	}

}
