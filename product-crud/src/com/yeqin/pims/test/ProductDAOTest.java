package com.yeqin.pims.test;


import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import com.yeqin.pims.dao.IProductDAO;
import com.yeqin.pims.dao.impl.ProductDAOImpl;
import com.yeqin.pims.domain.Product;
import com.yeqin.pims.query.ProductQueryObject;

public class ProductDAOTest {
	private IProductDAO dao = new ProductDAOImpl();
	@Test
	public void testListAll() {
		List<Product> list = dao.listAll();
		System.out.println("查询到"+list.size()+"条信息：");
		for (Product product : list) {
			System.out.println(product);
		}
	}

	@Test
	public void testQuery() {
		ProductQueryObject qo = new ProductQueryObject();
		qo.setName("java");
		qo.setMinprice(new BigDecimal(90));
		List<Product> list = dao.query(qo);
		System.out.println("查询到"+list.size()+"条信息：");
		for (Product product : list) {
			System.out.println(product);
		}
	}

}
