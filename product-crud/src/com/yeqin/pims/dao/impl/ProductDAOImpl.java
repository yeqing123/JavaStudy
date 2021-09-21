package com.yeqin.pims.dao.impl;

import java.util.List;


import com.yeqin.pims.dao.IProductDAO;
import com.yeqin.pims.domain.Product;
import com.yeqin.pims.query.ProductQueryObject;
import com.yeqin.pims.util.JdbcTemplate;
import com.yeqin.pims.util.handler.BeanListHandler;

public class ProductDAOImpl implements IProductDAO {

	public List<Product> listAll() {
		String sql = "SELECT * FROM product";
		return JdbcTemplate.query(sql, new BeanListHandler<Product>(Product.class));
	}

	public List<Product> query(ProductQueryObject qo) {
		String sql = "SELECT * FROM product";
		sql += qo.getQuery();
		System.out.println("SQL: " + sql);
		System.out.println("参数：" + qo.getParameters());
		return JdbcTemplate.query(sql.toString(), new BeanListHandler<Product>(Product.class), qo.getParameters().toArray());
	}

}
