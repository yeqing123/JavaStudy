package com.yeqin.pims.test;


import org.junit.Test;

import com.yeqin.pims.dao.IProductDirDAO;
import com.yeqin.pims.dao.impl.ProductDirDAOImpl;

public class ProductDirDAOTest {
    IProductDirDAO dao = new ProductDirDAOImpl();
	@Test
	public void testListAll() {
		System.out.println(dao.listAll());
	}

}
