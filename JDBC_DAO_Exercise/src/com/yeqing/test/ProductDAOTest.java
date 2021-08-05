package com.yeqing.test;

import org.junit.Test;

import com.yeqing.dao.IProductDAO;
import com.yeqing.dao.impl.ProductDAOImpl;
import com.yeqing.domain.Product;

public class ProductDAOTest {
    private IProductDAO productDAO = new ProductDAOImpl();
	@Test
	public void testSave() {
		Product p = new Product();
		p.setName("光盘");
		p.setNote("用于存储数据");
		p.setPrice(5.0D);
		p.setCount(100);
		p.setTypes_id(2);
		p.setSubtypes_id(8);
		productDAO.save(p);
	}

	@Test
	public void testDelete() {
		productDAO.delete(100L);
	}

	@Test
	public void testUpdate() {
		Product p = new Product();
		p.setName("篮球");
		p.setNote("结实耐用，品质保障");
		p.setPrice(200.0D);
		p.setCount(10);
		p.setTypes_id(3);
		p.setSubtypes_id(9);
		productDAO.update(30L, p);
	}

	@Test
	public void testGet() {
		System.out.println(productDAO.get(6L));
	}

	@Test
	public void testGetAll() {
	    for (Product p : productDAO.getAll()) {
			System.out.println(p);
		}
	}

}
