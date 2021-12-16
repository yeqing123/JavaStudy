package com.yeqing.moneylog;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yeqing.moneylog.domain.MoneyLog;
import com.yeqing.moneylog.mapper.MoneyLogMapper;
import com.yeqing.moneylog.query.QueryObject;
import com.yeqing.moneylog.service.IMoneyLogService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitConfig(locations = "classpath:applicationConfig.xml")
public class AppTest {

	@Autowired
	private MoneyLogMapper mapper;
	@Autowired
	private IMoneyLogService service;	
	@Test
	public void test() throws Exception {
		List<MoneyLog> list = mapper.selectAll();
		for (MoneyLog moneyLog : list) {
			System.out.println(moneyLog);
		}
	}
	@Test
	public void testService() throws Exception {
		List<MoneyLog> list = service.listAll();
		for (MoneyLog moneyLog : list) {
			System.out.println(moneyLog);
		}
	}
	@Test
	public void testQuery() throws Exception {
		QueryObject qo = new QueryObject();
		qo.setMinMoney(500);
		qo.setMaxMoney(1000);
		//qo.setKeyword("叶");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		qo.setStartDate(sdf.parse("2021-1-1"));
//		qo.setEndDate(sdf.parse("2021-5-31"));
		PageHelper.startPage(7, 5);
		System.out.println(qo);
		List<MoneyLog> list = mapper.query(qo);
		PageInfo<MoneyLog> pageInfo = new PageInfo<>(list);
		for (MoneyLog moneyLog : pageInfo.getList()) {
			System.out.println(moneyLog);
		}
		System.out.println("rowTotal: " + pageInfo.getTotal());
		System.out.println("currentPage: " + pageInfo.getPageNum() + "/" + pageInfo.getPages());
		System.out.println("previousPage: " + pageInfo.getPrePage());
		System.out.println("nextPage: " + pageInfo.getNextPage());
		System.out.println("pages: " + pageInfo.getPages());
		
	}
	
}
