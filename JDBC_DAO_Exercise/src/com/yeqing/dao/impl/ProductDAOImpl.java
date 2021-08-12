package com.yeqing.dao.impl;

import java.util.List;

import com.yeqing.dao.IProductDAO;
import com.yeqing.domain.Product;
import com.yeqing.jdbc.util.Hibernate;

public class ProductDAOImpl implements IProductDAO {

	@Override
	public void save(Product p) {
        Hibernate.save(p);
	}

	@Override
	public void delete(Long pid) {
		Hibernate.delete(pid, Product.class);
	}

	@Override
	public void update(Long pid, Product newP) {
		Hibernate.update(pid, newP);
	}

	@Override
	public Product get(Long pid) {
		return Hibernate.get(pid, Product.class);
	}

	@Override
	public List<Product> getAll() {
		return Hibernate.listAll(Product.class);
	}

}
