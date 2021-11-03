package com.yeqing.mybatis.product.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yeqing.mybatis.product.domain.Product;
import com.yeqing.mybatis.product.query.QueryObject;

public interface ProductMapper {
	
    List<Product> query(@Param("qo")QueryObject qo);

	Product get(Long pid);

	void save(@Param("p")Product p);

	void update(@Param("p")Product p);

	void delete(Long id);
}
