package com.yeqing.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yeqing.mybatis.mapper.EmployeeMapper;
import com.yeqing.mybatis.query.EmployeeQueryObject;
import com.yeqing.mybatis.query.PageResult;
import com.yeqing.mybatis.service.impl.QueryServiceImpl;
import com.yeqing.mybatis.util.MybatisUtil;

//对数据库的高级查询和分页查询进行测试
public class App {
	// 因为分页查询需要知道表中所有符合条件的数据的数量，因此我们先测试获取employee表中所有符合条件的数据的总数
	@Test
	public void test1() throws Exception {
		SqlSession session = MybatisUtil.getSession();
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		EmployeeQueryObject qo = new EmployeeQueryObject();
		int totalCount = mapper.getCount(qo);
		System.out.println(totalCount);
		session.close();
	}

	// 测试高级查询和分页查询
	@Test
	public void testAdvanceQuery() throws Exception {

		EmployeeQueryObject qo = new EmployeeQueryObject();
//		qo.setKeyword("吴");
//		qo.setMinsalary(new BigDecimal(1000));
//		qo.setMaxsalary(new BigDecimal(2000));
		qo.setDeptId(10L);
		qo.setCurrentPage(1);
		qo.setPageSize(2);
		PageResult pr = new QueryServiceImpl().query(qo);
		System.out.println("一共查询到" + pr.getTotalCount() + "条数据，每页最多显示" + pr.getPageSize() + "条数据");
		for (Object o : pr.getResult()) {
			System.out.println(o);
		}
		System.out.println("上页：" + pr.getPrevPage() + "  下页：" + pr.getNextPage() + "  当前页：" + pr.getCurrentPage() + "  末页："
				+ pr.getTotalPage());
	}
}
