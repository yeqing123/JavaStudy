package com.yeqing.pms.dao.impl;

import com.yeqing.pms.dao.ICommonDAO;
import com.yeqing.pms.domain.ProductDir;
import com.yeqing.pms.page.PageResult;
import com.yeqing.pms.query.IQuery;
import com.yeqing.pms.util.hibernate.Hibernate;

public class ProductDirDAOImpl implements ICommonDAO {

	public void save(Object obj) {
		Hibernate.save(ProductDir.class, obj);
	}

	public void delete(Long id) {
		Hibernate.delete(ProductDir.class, id);
	}

	public void update(Object obj) {
		Hibernate.update(ProductDir.class, obj);
	}

	public ProductDir get(Long id) {
		return (ProductDir) Hibernate.get(ProductDir.class, id);
	}

	public PageResult query(IQuery qo) {
		return Hibernate.query(ProductDir.class, qo);
	}

}
