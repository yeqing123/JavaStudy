package com.yeqing.dao;

import java.util.List;

import com.yeqing.domain.Product;

public interface IProductDAO {
	/**
	 * 保存一个商品信息
	 * @param p 商品信息对象
	 */
    void save(Product p);
    /**
     * 删除一条指定的商品信息
     * @param pid  被删除商品的ID
     */
    void delete(Long pid);
    /**
     * 修改指定商品的信息
     * @param pid 被修改的商品ID
     * @param newP 商品新的信息
     */
    void update(Long pid, Product newP);
    /**
     * 获得指定商品的信息
     * @param pid 指定商品ID
     * @return 返回指定商品信息
     */
    Product get(Long pid);
    /**
     * 获得所有商品的信息
     * @return 返回一个包含所有商品信息的集合
     */
    List<Product> getAll();
}
