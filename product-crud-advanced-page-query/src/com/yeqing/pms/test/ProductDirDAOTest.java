package com.yeqing.pms.test;

import org.junit.Test;

import com.yeqing.pms.dao.ICommonDAO;
import com.yeqing.pms.dao.impl.ProductDirDAOImpl;
import com.yeqing.pms.domain.ProductDir;
import com.yeqing.pms.page.PageResult;
import com.yeqing.pms.query.ProductDirQueryObject;

public class ProductDirDAOTest {
    ICommonDAO dao = new ProductDirDAOImpl();
	@Test
	public void testSave() {
		ProductDir dir = new ProductDir();
		dir.setDirName("娱乐类");
		dir.setNote("游戏、旅行、娱乐类");
		dir.setProfitMargin(0.8f);
		dao.save(dir);
	}

	@Test
	public void testDelete() {
		dao.delete(5L);
	}

	@Test
	public void testUpdate() {
		ProductDir dir = (ProductDir) dao.get(4L);
		dir.setNote("旅行、娱乐、游戏类");
		dao.update(dir);
	}

	@Test
	public void testGet() {
	}

	@Test
	public void testQuery() {
		ProductDirQueryObject qo = new ProductDirQueryObject();
		qo.setCurrentPage(1);
		qo.setPageSize(2);
//		qc.setMinProfitMargin(0.5f);
//		qc.setMaxProfitMargin(0.6f);
		qo.setDirName("娱乐");
		PageResult ps = dao.query(qo);
		for (Object obj : ps.getListData()) {
			System.out.println(obj);
		}
		System.out.println("上页：" + ps.getPrevPage() + ", 下页：" + ps.getNextPage() + ", 末页：" + ps.getTotalPage()
				+ ", 当前页[" + ps.getCurrentPage() + "]" + ", 一共查询到" + ps.getTotalCount() + "条数据， 每页" + ps.getPageSize()
				+ "条数据");
	}

}
