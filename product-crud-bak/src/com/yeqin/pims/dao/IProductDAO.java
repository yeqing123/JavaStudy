package com.yeqin.pims.dao;

import java.util.List;

import com.yeqin.pims.domain.Product;

public interface IProductDAO {
    /**
     * 添加商品信息
     * @param obj 新商品的信息
     */
	void save(Product obj);
	
	/**
	 * 删除指定商品信息
	 * @param id 被删除商品的ID
	 */
	void delete(Long id);
	/**
	 * 修改指定商品信息
	 * @param obj 被修改商品的新信息
	 */
	void update(Product obj);
	/**
	 * 查询指定商品信息
	 * @param id 指定商品的ID
	 * @return 返回一个封装了被查询商品信息的对象
	 */
	Product get(Long id);
	/**
	 * 查询所有的商品信息
	 * @return 返回一个包含了所有商品信息的List集合
	 */
	List<Product> listAll();
}
