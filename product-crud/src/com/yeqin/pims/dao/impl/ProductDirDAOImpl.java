package com.yeqin.pims.dao.impl;

import java.util.List;

import com.yeqin.pims.dao.IProductDirDAO;
import com.yeqin.pims.domain.ProductDir;
import com.yeqin.pims.query.ProductDirQueryObject;
import com.yeqin.pims.util.JdbcTemplate;
import com.yeqin.pims.util.handler.BeanListHandler;

public class ProductDirDAOImpl implements IProductDirDAO {

	public List<ProductDir> listAll() {
		String sql = "SELECT * FROM product_dir";
		return JdbcTemplate.query(sql, new BeanListHandler<ProductDir>(ProductDir.class));
	}

	@Override
	public List<ProductDir> query(ProductDirQueryObject qo) {
		// TODO Auto-generated method stub
		return null;
	}

}
