package com.yeqin.pims.dao.impl;

import java.util.List;

import com.yeqin.pims.dao.IProductDirDAO;
import com.yeqin.pims.domain.ProductDir;
import com.yeqin.pims.page.PageResult;
import com.yeqin.pims.query.IQuery;

@SuppressWarnings("unchecked")
public class ProductDirDAOImpl extends BaseQuery implements IProductDirDAO {

	public List<ProductDir> listAll() {
		return (List<ProductDir>) super.listAll(ProductDir.class);
	}

	public PageResult query(IQuery qo) {
		return super.query(ProductDir.class, qo);
	}

}
