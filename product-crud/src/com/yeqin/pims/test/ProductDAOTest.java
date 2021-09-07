package com.yeqin.pims.test;


import java.math.BigDecimal;

import org.junit.Test;

import com.yeqin.pims.dao.IProductDAO;
import com.yeqin.pims.dao.impl.ProductDAOImpl;
import com.yeqin.pims.domain.Product;

public class ProductDAOTest {
    private IProductDAO dao = new ProductDAOImpl();
	@Test
	public void testSave() {
		Product p = new Product();
		p.setName("只是为了好玩");
		p.setNote("一本关于Linux之父Linus Torvalds的自传，本书协作风格风趣幽默，不但包含了作者本人的成长经历，还有他对Linux系统及开源理念的看法。");
		p.setPrice(new BigDecimal(49.00));
		p.setCount(10);
	//	p.setId(106L);
		dao.save(p);
	}

	@Test
	public void testDelete() {
		dao.delete(108L);
	}

	@Test
	public void testUpdate() {
		Product p = new Product();
		p.setId(101L);
		p.setName("信息简史");
		p.setNote("一本讲述了关于信息发展历史的书籍。");
		p.setPrice(new BigDecimal(50.00));
		p.setCount(20);
		dao.update(p);
	}

	@Test
	public void testGet() {
		System.out.println(dao.get(10L));
	}

	@Test
	public void testListAll() {
		System.out.println(dao.listAll());
	}

}
