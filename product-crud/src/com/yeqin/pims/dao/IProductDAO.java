package com.yeqin.pims.dao;

import java.util.List;

import com.yeqin.pims.domain.Product;
import com.yeqin.pims.query.ProductQueryObject;

public interface IProductDAO {
    /**
     * 查询所有商品信息
     * @return 返回包含了商品信息对象的集合
     */
	List<Product> listAll();
	/**
	 * 查询符合条件的所有商品信息
	 * @param qo 封装了查询条件的对象
	 * @return 返回包含了商品信息对象的集合
	 */
	List<Product> query(ProductQueryObject qo);
}
