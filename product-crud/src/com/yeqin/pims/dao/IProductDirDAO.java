package com.yeqin.pims.dao;

import java.util.List;

import com.yeqin.pims.domain.ProductDir;
import com.yeqin.pims.query.ProductDirQueryObject;

public interface IProductDirDAO {
    
	List<ProductDir> listAll();
	
	List<ProductDir> query(ProductDirQueryObject qo);
}
