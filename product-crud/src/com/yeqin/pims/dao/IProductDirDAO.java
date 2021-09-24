package com.yeqin.pims.dao;

import java.util.List;

import com.yeqin.pims.domain.ProductDir;
import com.yeqin.pims.page.PageResult;
import com.yeqin.pims.query.IQuery;

public interface IProductDirDAO {
	
	List<ProductDir> listAll();
	
	PageResult query(IQuery qO);
}
