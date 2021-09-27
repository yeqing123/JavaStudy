package com.yeqing.pms.test;

import java.math.BigDecimal;

import org.junit.Test;

import com.yeqing.pms.dao.ICommonDAO;
import com.yeqing.pms.dao.impl.ProductDAOImpl;
import com.yeqing.pms.domain.Product;
import com.yeqing.pms.page.PageResult;
import com.yeqing.pms.query.ProductQueryObject;

public class ProductDAOTest {
	ICommonDAO dao = new ProductDAOImpl();

	@Test
	public void testSave() {
		Product p = new Product();
		p.setName("美女鼠标");
		p.setNote("专门为美女使用的鼠标");
		p.setDir(3L);
		p.setPrice(new BigDecimal(100));
		p.setCount(10);
		p.setId(123L);
		dao.save(p);
	}

	@Test
	public void testDelete() {
		dao.delete(106L);
	}

	@Test
	public void testUpdate() {
		Product p = (Product) dao.get(102L);
		p.setName("机械键盘");
		dao.update(p);
	}

	@Test
	public void testGet() {
		System.out.println(dao.get(100L));
	}

	@Test
	public void testQuery() {
		ProductQueryObject qo = new ProductQueryObject();
		//qc.setName("鼠标");
		qo.setKeyword("web");
		qo.setCurrentPage(1);
		qo.setPageSize(5);
		PageResult ps = dao.query(qo);
		for (Object obj : ps.getListData()) {
			System.out.println(obj);
		}
		System.out.println("上页：" + ps.getPrevPage() + ", 下页：" + ps.getNextPage() + ", 末页：" + ps.getTotalPage()
				+ ", 当前页[" + ps.getCurrentPage() + "]" + ", 一共查询到" + ps.getTotalCount() + "条数据， 每页" + ps.getPageSize()
				+ "条数据");
	}

}
