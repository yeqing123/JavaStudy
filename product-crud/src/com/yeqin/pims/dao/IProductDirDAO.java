package com.yeqin.pims.dao;

import java.util.List;

import com.yeqin.pims.domain.ProductDir;

public interface IProductDirDAO {
	/**
	 * 添加商品目录信息
	 * 
	 * @param obj 新目录的信息
	 */
	void save(ProductDir obj);

	/**
	 * 删除指定目录信息
	 * 
	 * @param id 被删除目录的ID
	 */
	void delete(Long id);

	/**
	 * 修改指定目录信息
	 * 
	 * @param obj 被修改目录的新信息
	 */
	void update(ProductDir obj);

	/**
	 * 查询指定目录信息
	 * 
	 * @param id 指定目录的ID
	 * @return 返回一个封装了被查询目录信息的对象
	 */
	ProductDir get(Long id);

	/**
	 * 查询所有的目录信息
	 * 
	 * @return 返回一个包含了所有目录信息的List集合
	 */
	List<ProductDir> listAll();

}
