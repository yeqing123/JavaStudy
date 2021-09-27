package com.yeqing.pms.dao.impl;

import com.yeqing.pms.dao.ICommonDAO;
import com.yeqing.pms.domain.Product;
import com.yeqing.pms.page.PageResult;
import com.yeqing.pms.query.IQuery;
import com.yeqing.pms.util.hibernate.Hibernate;

public class ProductDAOImpl implements ICommonDAO {

	public void save(Object obj) {
        Hibernate.save(Product.class, obj);
	}

	@Override
	public void delete(Long id) {
		Hibernate.delete(Product.class, id);
	}

	@Override
	public void update(Object obj) {
        Hibernate.update(Product.class, obj);
	}

	@Override
	public Product get(Long id) {
		return (Product) Hibernate.get(Product.class, id);
	}

	@Override
	public PageResult query(IQuery qo) {
		return Hibernate.query(Product.class, qo);
	}


}
