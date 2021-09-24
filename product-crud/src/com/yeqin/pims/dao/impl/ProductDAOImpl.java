package com.yeqin.pims.dao.impl;

import java.util.List;

import com.yeqin.pims.dao.IProductDAO;
import com.yeqin.pims.domain.Product;
import com.yeqin.pims.page.PageResult;
import com.yeqin.pims.query.IQuery;

@SuppressWarnings("unchecked")
public class ProductDAOImpl extends BaseQuery implements IProductDAO {

	public List<Product> listAll() {
		return (List<Product>) super.listAll(Product.class);
	}

	public PageResult query(IQuery qo) {
		return super.query(Product.class, qo);
	}

}
