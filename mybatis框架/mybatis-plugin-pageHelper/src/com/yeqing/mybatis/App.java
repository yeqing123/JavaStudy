package com.yeqing.mybatis;


import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yeqing.mybatis.domain.Employee;
import com.yeqing.mybatis.mapper.EmployeeMapper;
import com.yeqing.mybatis.query.EmployeeQueryObject;
import com.yeqing.mybatis.service.impl.QueryServiceImpl;
import com.yeqing.mybatis.util.MybatisUtil;

public class App {
	
	@Test//使用PageHelper来分页查询employee表中的所有数据
	void testListAll() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		PageHelper.startPage(1, 3);   //使用PageHelper来设置当前页和每页最多显示的数据
		List<Employee> list = mapper.listAll();
		PageInfo<Employee> pageInfo = new PageInfo<>(list);
		System.out.println("一共查询到" + pageInfo.getTotal() + "条数据，每页最多显示" + pageInfo.getPageSize() + "条数据");
		for (Object o : pageInfo.getList()) {
			System.out.println(o);
		}
		System.out.println("上页：" + pageInfo.getPrePage() + "  下页：" + pageInfo.getNextPage() + "  当前页：" + pageInfo.getPageNum() + "  末页："
				+ pageInfo.getPages());
		session.close();
	}
	
	// 使用PageHelper执行高级查询和分页查询
	@Test
	public void testAdvanceQuery() throws Exception {
		EmployeeQueryObject qo = new EmployeeQueryObject();
//		qo.setMinsalary(new BigDecimal(1000));
		qo.setMaxsalary(new BigDecimal(2000));
//		qo.setDeptId(10L);
		qo.setCurrentPage(2);
		qo.setPageSize(3);
		PageInfo<Employee> pageInfo = new QueryServiceImpl().query(qo);
		System.out.println("一共查询到" + pageInfo.getTotal() + "条数据，每页最多显示" + pageInfo.getPageSize() + "条数据");
		for (Object o : pageInfo.getList()) {
			System.out.println(o);
		}
		System.out.println("上页：" + pageInfo.getPrePage() + "  下页：" + pageInfo.getNextPage() + "  当前页：" + pageInfo.getPageNum() + "  末页："
				+ pageInfo.getPages());
	}

}
