package com.yeqin.pims.dao;

import java.util.List;

import com.yeqin.pims.domain.Product;
import com.yeqin.pims.page.PageResult;
import com.yeqin.pims.query.IQuery;

public interface IProductDAO {
	
	List<Product> listAll();
	PageResult query(IQuery qO);

}
