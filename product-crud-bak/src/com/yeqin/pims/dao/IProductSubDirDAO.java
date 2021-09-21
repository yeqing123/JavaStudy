package com.yeqin.pims.dao;

import java.util.List;

import com.yeqin.pims.domain.ProductSubDir;

public interface IProductSubDirDAO {
	/**
	 * 添加商品子目录信息
	 * 
	 * @param obj 新子目录的信息
	 */
	void save(ProductSubDir obj);

	/**
	 * 删除指定子目录信息
	 * 
	 * @param id 被删除子目录的ID
	 */
	void delete(Long id);

	/**
	 * 修改指定子目录信息
	 * 
	 * @param obj 被修改子目录的新信息
	 */
	void update(ProductSubDir obj);

	/**
	 * 查询指定子目录信息
	 * 
	 * @param id 指定子目录的ID
	 * @return 返回一个封装了被查询子目录信息的对象
	 */
	ProductSubDir get(Long id);

	/**
	 * 查询所有的子目录信息
	 * 
	 * @return 返回一个包含了所有子目录信息的List集合
	 */
	List<ProductSubDir> listAll();
}
