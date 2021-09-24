package com.yeqin.pims.test;


import org.junit.Test;

import com.yeqin.pims.dao.IProductDirDAO;
import com.yeqin.pims.dao.impl.ProductDirDAOImpl;
import com.yeqin.pims.page.PageResult;
import com.yeqin.pims.query.ProductDirQueryObject;

public class ProductDirDAOTest {
    IProductDirDAO dao = new ProductDirDAOImpl();
    @Test
	public void testListAll() {
		System.out.println(dao.listAll());
	}
    @Test
	public void testQuery() throws Exception {
		ProductDirQueryObject qo = new ProductDirQueryObject();
		qo.setDirName("IT");
		PageResult ps = dao.query(qo);
		for(Object dir : ps.getListData()) {
			System.out.println(dir);
		}
	}
}
