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
		p.setName("键盘");
		p.setNote("用于输入数据");
		p.setPrice(100.0D);
		p.setCount(10);
		p.setTypes_id(2);
		p.setSubtypes_id(8);
		productDAO.save(p);
	}

	@Test
	public void testDelete() {
		productDAO.delete(102L);
	}

	@Test
	public void testUpdate() {
		Product p = new Product();
		p.setName("鼠标");
		p.setNote("激光鼠标");
		p.setPrice(20.0D);
		p.setCount(10);
		p.setTypes_id(3);
		p.setSubtypes_id(8);
		productDAO.update(102L, p);
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
